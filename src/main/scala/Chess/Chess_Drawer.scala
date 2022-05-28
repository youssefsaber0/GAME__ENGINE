import javafx.scene.image.Image
import scalafx.scene.canvas

class Chess_Drawer extends IDrawer {
  def image(piece: Char):Image =piece match {
    case 'R' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\BR.png");
    case 'N' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\BN.png");
    case 'B' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\BB.png");
    case 'Q' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\BQ.png");
    case 'K' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\BK.png");
    case 'P' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\BP.png");
    case 'r' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\WR.png");
    case 'n' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\WN.png");
    case 'b' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\WB.png");
    case 'q' => new Image(System.getProperty("user.dir") + "\\images\\chess\\WQ.png");
    case 'k' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\WK.png");
    case 'p' => new Image(System.getProperty("user.dir") + "\\images\\Chess\\WP.png");
  }

  override def draw(gc: canvas.GraphicsContext, board: Array[Array[Char]]): Unit = {
    val boardI = new Image(System.getProperty("user.dir") + "\\images\\Chess\\board.png");
    gc.drawImage(boardI, 0, 0, 552, 552)
    for (i <- 0 to 7; j <- 0 to 7) {
      if(board(i)(j)!='-') {
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

