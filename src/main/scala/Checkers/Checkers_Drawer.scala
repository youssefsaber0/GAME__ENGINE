import javafx.scene.image.Image
import scalafx.scene.canvas

class Checkers_Drawer extends IDrawer {
  def image(piece: Char): Image = piece match {
    case 'K' => new Image(System.getProperty("user.dir") + "\\images\\CHECKER\\BKING.png");
    case 'P' => new Image(System.getProperty("user.dir") + "\\images\\CHECKER\\BLAACK.png");
    case 'k' => new Image(System.getProperty("user.dir") + "\\images\\CHECKER\\WKINGG.png");
    case 'p' => new Image(System.getProperty("user.dir") + "\\images\\CHECKER\\WHITE.png");
  }

  override def draw(gc: canvas.GraphicsContext, board: Array[Array[Char]]): Unit = {
    val boardI = new Image(System.getProperty("user.dir") + "\\images\\CHECKER\\board.png");
    gc.drawImage(boardI, 0, 0, 552, 552)
    for (i <- 0 to 7; j <- 0 to 7) {
      if (board(i)(j) != '-') {
        gc.drawImage(image(board(i)(j)), j * 69 + 4, i * 69 + 4, 60, 60)
      }
    }
  }

  override def movePieceToCursor(gc: canvas.GraphicsContext, board: Array[Array[Char]], selectedPieceIndex: (Int,Int), cursor: (Int,Int)): Unit = {
    val boardI = new Image(System.getProperty("user.dir") + "\\images\\Chess\\board.png");
    gc.drawImage(boardI, 0, 0, 552, 552)
    for (i <- 0 to 7; j <- 0 to 7) {
      if (i == selectedPieceIndex._1 && j == selectedPieceIndex._2) {
        gc.drawImage(image(board(i)(j)), cursor._2 - 30, cursor._1 -30, 60, 60)
      }
      else if(board(i)(j)!='-') {
        gc.drawImage(image(board(i)(j)), j * 69 + 4, i * 69 + 4, 60, 60)
      }
    }
  }
}
