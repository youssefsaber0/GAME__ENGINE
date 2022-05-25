package Checkers.view


import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image

class Drawer {
  def image(piece: Char):Image =piece match{
    case 'K' => new Image(System.getProperty("user.dir") + "\\image\\CHECKER\\BKING.png");
    case 'P' => new Image(System.getProperty("user.dir") + "\\image\\CHECKER\\BLAACK.png");
    case 'k' => new Image(System.getProperty("user.dir") + "\\image\\CHECKER\\WKINGG.png");
    case 'p'=> new Image(System.getProperty("user.dir") + "\\image\\CHECKER\\WHITE.png");

  }
  def drawGame(gc: GraphicsContext, checkerBoard: Array[Array[Char]]): Unit = {
    val board = new Image(System.getProperty("user.dir") + "\\image\\CHECKER\\board.png");
    gc.drawImage(board, 0, 0, 552, 552)
    for (i <- 0 to 7; j <- 0 to 7) {
      if(checkerBoard(i)(j)!='-') {
          gc.drawImage(image(checkerBoard(i)(j)), j * 69 + 4, i * 69 + 4, 60, 60)
        }
    }
  }
}