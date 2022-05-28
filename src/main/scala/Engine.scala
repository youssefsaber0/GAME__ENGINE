import javafx.scene.input.MouseEvent
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.control.Label

object Engine {

  def getControllerAndDrawer(game: Int): (IController, IDrawer) = game match{
    case 0 => (new XO_Controller(), new XO_Drawer())
    case 1 => (new Chess_Controller(), new Chess_Drawer())
    case 2 => (new Checkers_Controller(), new Checkers_Drawer())
    case 3 => (new Connect4_Controller(), new Connect4_Drawer())
  }


  def selectGame(game: Int, canvas: Canvas, label: Label): Unit = {
    val (controller, drawer): (IController, IDrawer) = getControllerAndDrawer(game)
    startGame(controller.init(), controller.gameMovesPieces, controller.getLogicalPos, controller.runGame, drawer.draw, drawer.movePieceToCursor, canvas, label)
  }

  /* Game Engine that takes controller and drawer of the selected board game and
  start rendering the game and validate player moves */

  def startGame(board: Array[Array[Char]], gameMovesPieces:() => Boolean,
                getLogicalPos: ((Int,Int)) => (Int,Int), controller: (Array[Array[Char]], Array[(Int, Int)], Boolean) => Boolean,
                 drawer: (GraphicsContext, Array[Array[Char]]) => Unit,
                movePieceToCursor: (GraphicsContext, Array[Array[Char]], (Int,Int), (Int,Int)) => Unit
                 , canvas: Canvas, label: Label): Unit = {
    var positions: Array[(Int, Int)] = Array()
    var playerTurn = true
    drawer(canvas.graphicsContext2D, board)

    var pieceSelected = false
    var selectPieceIndex: (Int,Int) = (0,2)
    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,  (event: MouseEvent) => {
      if (gameMovesPieces()) {
        selectPieceIndex = getLogicalPos((event.getY.toInt, event.getX.toInt))
        if (board(selectPieceIndex._1)(selectPieceIndex._2) != '-')
          pieceSelected = true
        positions = positions :+ (event.getY.toInt, event.getX.toInt)
      }
    })

    canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (event: MouseEvent) => {
      if (pieceSelected) {
        movePieceToCursor(canvas.graphicsContext2D, board, selectPieceIndex, (event.getY.toInt, event.getX.toInt))
      }
    })

    canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (event: MouseEvent) => {
      positions = positions :+ (event.getY.toInt, event.getX.toInt)
      if (controller(board, positions, playerTurn)) {
        playerTurn = switchTurns(playerTurn, label)
      }
      drawer(canvas.graphicsContext2D, board)
      positions = Array()
      pieceSelected = false
    })
  }

  def switchTurns(turn : Boolean, label: Label): Boolean = {
    val num = if (!turn) 1 else 2
    label.setText(s"Player ${num} turn")
    !turn
  }

}

