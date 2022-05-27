import javafx.scene.Cursor
import scalafx.application.JFXApp
import scalafx.scene.control.Button
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.{Group, Scene}

object Main{
  def main(args: Array[String]): Unit = {
//    val drawer = new XO_Drawer()
//    drawer.draw(List('x', 'o', ' ', ' ', 'o', 'x', 'x', ' ', 'o'), 'A')
      val controller = new XO_Controller()
      controller.Init();
  }
//  def draw():Unit ={
//    val app = new JFXApp {
//      stage = new JFXApp.PrimaryStage {
//        title = "Tic tac toe"
//        val root = new Group()
//        scene = new Scene(root,410, 600, Color.web("0x5AAB61")) {
//          val image = new Image("https://i.pinimg.com/originals/41/59/b6/4159b67ca0f25e315be0c98993e91769.jpg", 390, 390, true, true)
//          val board = new ImageView(image)
//          board.layoutX = 10
//          board.layoutY = 10
//          board.setCursor(Cursor.HAND)
//          board.setOnMouseClicked((e) => {(e.getX, e.getY)})
//          content = board
//        }
//      }
//      stage.setMaxWidth(410)
//      stage.setMaxHeight(600)
//      stage.resizable = false
//    }
//    app.main(Array(""))
//  }
}

