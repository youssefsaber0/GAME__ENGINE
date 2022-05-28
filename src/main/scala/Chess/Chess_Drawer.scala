import javafx.scene.image.Image
import scalafx.scene.canvas

class Chess_Drawer extends IDrawer {
  def image(piece: Char):Image =piece match {
    case 'R' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\BR.png");
    case 'N' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\BN.png");
    case 'B' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\BB.png");
    case 'Q' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\BQ.png");
    case 'K' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\BK.png");
    case 'P' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\BP.png");
    case 'r' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\WR.png");
    case 'n' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\WN.png");
    case 'b' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\WB.png");
    case 'q' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\WQ.png");
    case 'k' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\WK.png");
    case 'p' => new Image(System.getProperty("user.dir") + "\\images\\CHESS\\WP.png");
  }

  override def draw(gc: canvas.GraphicsContext, board: Array[Array[Char]]): Unit = {
    val boardI = new Image(System.getProperty("user.dir") + "\\images\\CHESS\\board.png");
    gc.drawImage(boardI, 0, 0, 552, 552)
    for (i <- 0 to 7; j <- 0 to 7) {
      if(board(i)(j)!='-') {
        gc.drawImage(image(board(i)(j)), j * 69 + 4, i * 69 + 4, 60, 60)
      }
    }
  }
}

