import javafx.scene.Cursor
import scalafx.application.JFXApp
import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight}
import scalafx.scene.{Group, Node, Scene}


class XO_Drawer {
  val controller : XO_Controller = new XO_Controller()
  def draw(pieces: List[Char], turnChar: Char): Unit = {
    var charTurn = turnChar
    var input : (Int, Int) = (-1, -1)
    val app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        maxHeight = 500; maxWidth = 410; resizable = false; title = "Tic tac toe"
        val root = new Group()
        scene = new Scene(root, 410, 500, Color.web("0x5AAB61")) {
          var board : ImageView = drawBoard()
          var piecesAdded : List[Node] = drawPieces(pieces)
          var turn: Label = showPlayer(charTurn)
          piecesAdded = board :: piecesAdded
          piecesAdded = turn :: piecesAdded
          content = piecesAdded
          board.setOnMouseClicked(e => {
            if(e.getY < 45 || e.getY >= 345 || e.getX < 45 || e.getX >= 345) input = (-1,  -1)
            else input = ((e.getY - 45).asInstanceOf[Int] / 100, (e.getX - 45).asInstanceOf[Int] / 100)
            println(input)
            val res = controller.play(input._1, input._2, charTurn)
            charTurn = res._2
            piecesAdded = drawPieces(res._1); turn = showPlayer(charTurn)
            piecesAdded = board :: piecesAdded; piecesAdded = turn :: piecesAdded
            content = piecesAdded
          })
        }
//      }
//    }
//    app.main(Array(""))
  }
  def drawBoard() : ImageView = {
    val image = new Image("https://i.pinimg.com/originals/41/59/b6/4159b67ca0f25e315be0c98993e91769.jpg",
      390, 390, true, true)
    val board = new ImageView(image)
    board.layoutX = 10; board.layoutY = 10; board.setCursor(Cursor.HAND)
    board
  }

  def drawPieces(pieces :List[Char]) : List[ImageView] = {
    var pieceContents : List[ImageView] = List()
    val pieceMap = getPieceMap(pieces)
    pieceMap.filter(x => x._2 != " " && x._2 != null).foreach(p =>{
        val image = new Image(p._2, 90, 90, true, true, false)
        val piece = new ImageView(image)
        piece.layoutX = p._1._1; piece.layoutY = p._1._2
      pieceContents = piece :: pieceContents
    })
    pieceContents
  }
  def showPlayer(player : Char) : Label ={
    val playerTurn = new Label(s"Player ${player}'s Turn")
    playerTurn.layoutX = 140; playerTurn.layoutY = 410
    playerTurn.font = Font("Comic Sans", FontWeight.Bold, 20)
    playerTurn
  }

  val getPieceMap: List[Char] => Map[(Double, Double), String]
  = pieces => (getPiecesPos(Range(0, 9).toList) zip getpiecesImg(pieces)).toMap

  val getPiecesPos: List[Int] => List[(Double, Double)]
  = pieces => pieces.map(p => getLogicalPos(p)).map(i => getActualPos(i._1, i._2))

  val getpiecesImg: List[Char] => List[String] = pieces => pieces.map(p => getpieceImage(p))

  val getpieceImage: Char => String = {
    case 'o' => System.getProperty("user.dir").concat("\\O-removebg-preview.PNG")
    case 'x' => System.getProperty("user.dir").concat("\\X-removebg-preview.PNG")
    case ' ' => " "
  }
  val getActualPos: (Int, Int) => (Double, Double) = (x, y) => (x * 100 + 60, y * 100 + 60)
  val getLogicalPos: Int => (Int, Int) = x => (x % 3, x / 3)
}
