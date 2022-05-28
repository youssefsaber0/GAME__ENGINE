import javafx.scene.input.MouseEvent
import scalafx.scene.canvas.{Canvas, GraphicsContext}

import scala.::

object Engine {

  def getControllerAndDrawer(game: Int): (IController, IDrawer) = game match{
    case 0 => (new XO_Controller(), new XO_Drawer())
    case 1 => (new Chess_Controller(), new Chess_Drawer())
    case 2 => (new Checkers_Controller(), new Checkers_Drawer())
    case 3 => (new Connect4_Controller(), new Connect4_Drawer())
  }


  def selectGame(game: Int, canvas: Canvas): Unit = {
    val (controller, drawer): (IController, IDrawer) = getControllerAndDrawer(game)
    startGame(controller.init(), controller.checkClicks, controller.runGame, drawer.draw, canvas)
  }

  /* Game Engine that takes controller and drawer of the selected board game and
  start rendering the game and validate player moves */

  def startGame(board: Array[Array[Char]], checkClicks: (Array[(Int, Int)]) => Boolean,
                 controller: (Array[Array[Char]], Array[(Int, Int)], Boolean) => Boolean,
                 drawer: (GraphicsContext, Array[Array[Char]]) => Unit
                 , canvas: Canvas): Unit = {
    var clicks: Array[(Int, Int)] = Array()
    var playerTurn = true
    drawer(canvas.graphicsContext2D, board)
    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (event: MouseEvent) => {
        def onMouseClicked(event: MouseEvent): Unit = {
          clicks = clicks :+ (event.getY.toInt, event.getX.toInt)
          if (checkClicks(clicks)) {
            if (controller(board, clicks, playerTurn)) {
              drawer(canvas.graphicsContext2D, board)
              playerTurn = switchTurns(playerTurn)
            }
            clicks = Array()
          }
        }
        onMouseClicked(event)
    })
  }

  def switchTurns(turn : Boolean): Boolean = !turn

}

