import javafx.scene.image.Image
import scalafx.scene.canvas

import java.io.FileInputStream

class Checkers_Drawer extends IDrawer {
  def image(piece: Char): Image = piece match {
    case 'K' => new Image(new FileInputStream("images/Checker/BKING.png"));
    case 'P' => new Image(new FileInputStream("images/Checker/BLAACK.png"));
    case 'k' => new Image(new FileInputStream("images/Checker/WKINGG.png"));
    case 'p' => new Image(new FileInputStream("images/Checker/WHITE.png"));
  }

  override def draw(gc: canvas.GraphicsContext, board: Array[Array[Char]]): Unit = {
    val boardI = new Image(new FileInputStream("images/CHECKER/board.png"));
    gc.drawImage(boardI, 0, 0, 552, 552)
    for (i <- 0 to 7; j <- 0 to 7) {
      if (board(i)(j) != '-') {
        gc.drawImage(image(board(i)(j)), j * 69 + 4, i * 69 + 4, 60, 60)
      }
    }
  }

  override def movePieceToCursor(gc: canvas.GraphicsContext, board: Array[Array[Char]], selectedPieceIndex: (Int,Int), cursor: (Int,Int)): Unit = {
    val boardI = new Image(new FileInputStream("images/Chess/board.png"));
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
