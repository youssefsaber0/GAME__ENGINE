import scalafx.application.JFXApp
import scalafx.scene.control.Button
import scalafx.scene.image.Image.sfxImage2jfx
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.scene.{Group, Scene}

object Main {
  def main(args: Array[String]): Unit = {

    var app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "BOARD GAMES ENGINE"
        val root = new Group
        scene = new Scene(root, 380, 400) {
          var rectangle: Rectangle = Rectangle(100, 100)
          rectangle.fill = Color.RED
          rectangle.setOnMouseClicked(e => println("Hello from rectangle"))
          var circle: Circle = Circle(500, 500, 50, Color.Blue)
          circle.setOnMouseClicked((e) => {
            println("Circle")
          })
          val button = new Button("click me")
          button.onMouseClicked = e => println("clicked")
          val image = new Image("https://i.pinimg.com/originals/41/59/b6/4159b67ca0f25e315be0c98993e91769.jpg")
          val imageView = new ImageView(image)
          imageView.onMouseClicked = e => println("image")

          content = List(rectangle, circle, button, imageView)
        }
      }
    }
    app.main(Array(""))
  }
}