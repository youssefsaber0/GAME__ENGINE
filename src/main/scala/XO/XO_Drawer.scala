import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image

class XO_Drawer extends IDrawer {
  override def draw(gc: GraphicsContext, board: Array[Array[Char]]): Unit = {
    drawBoard(gc)
    drawPieces(board)(gc)
  }

  val drawPieces: Array[Array[Char]] => GraphicsContext => Unit = pieces => {
    val getGC: GraphicsContext => Unit = gc => {
      for (i <- 0 to 2; j <- 0 to 2) {
        if(pieces(i)(j) != ' ') {
          val image = new Image(pieceUrl(pieces(i)(j)))
          gc.drawImage(image,getActualPos(j),getActualPos(i), 95, 95)
        }
      }
    }
    getGC
  }

  val drawBoard : GraphicsContext => Unit = gc => {
    val path = System.getProperty("user.dir").concat("\\images\\tic-tac-toe\\XO.jpg")
    val image = new Image(path)
    gc.drawImage(image, 10, 10, 390, 390)
  }


  val pieceUrl: Char => String = {
    case 'o' => System.getProperty("user.dir").concat("\\images\\tic-tac-toe\\O-removebg-preview.PNG")
    case 'x' => System.getProperty("user.dir").concat("\\images\\tic-tac-toe\\X-removebg-preview.PNG")
  }

  val getActualPos: Int => Double = x => x * 100 + 55

  override def movePieceToCursor(gc: GraphicsContext, board: Array[Array[Char]], selectedPieceIndex: (Int,Int), cursor: (Int,Int)): Unit = {}

}
