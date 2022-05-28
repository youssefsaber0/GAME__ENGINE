import javafx.scene.canvas
import javafx.scene.image.Image
import javafx.scene.shape.Circle
import scalafx.scene.canvas.GraphicsContext;



class Connect4_Drawer extends IDrawer {

  override def draw(gc: GraphicsContext, board: Array[Array[Char]]): Unit = {
    val boardImage = new Image("C:\\Users\\Dell\\IdeaProjects\\BOARD_GAMES_ENGINE\\images\\CONNECT4\\board.png")
    gc.drawImage(boardImage, 0, 0, 552, 552)
    for (r <- 0 to 5; c <- 0 to 6) {
      if (board(r)(c) == 'Y') {
        val img = new Image("C:\\Users\\Dell\\IdeaProjects\\BOARD_GAMES_ENGINE\\images\\CONNECT4\\yellow-circle.png")
        gc.drawImage(img, 50 + (c*76), 76 + (80 * r), 55, 55)
      } else if (board(r)(c) == 'R') {
        val img = new Image("C:\\Users\\Dell\\IdeaProjects\\BOARD_GAMES_ENGINE\\images\\CONNECT4\\red-circle.png")
        gc.drawImage(img, 50 + (c*76), 76 + (80 * r), 55, 55)
      }
    }
  }

}
