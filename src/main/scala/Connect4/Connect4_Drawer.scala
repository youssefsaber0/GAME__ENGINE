import javafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext;

class Connect4_Drawer extends IDrawer {

  def getImg(c: Char): Image = c match {
    case 'Y' => new Image(System.getProperty("user.dir") + "\\images\\Connect4\\yellow-circle.png")
    case 'R' => new Image(System.getProperty("user.dir") + "\\images\\Connect4\\red-circle.png")
  }

  override def draw(gc: GraphicsContext, board: Array[Array[Char]]): Unit = {
    val boardImage = new Image(System.getProperty("user.dir") + "\\images\\Connect4\\board.png")
    gc.drawImage(boardImage, 0, 0, 552, 552)
    for (r <- 0 to 5; c <- 0 to 6) {
      val curChar = board(r)(c)
      if (curChar == 'Y' || curChar == 'R') {
        val img = getImg(curChar)
        gc.drawImage(img, 22 + (c*73), 38 + (81.2 * r), 70, 70)
      }
    }
  }

}
