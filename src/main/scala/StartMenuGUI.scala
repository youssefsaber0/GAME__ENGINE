import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.{Group, Scene}
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.Black
import scalafx.scene.text.Font

import java.io.FileInputStream
import scala.reflect.ClassManifestFactory.Null

object StartMenuGUI {

  def main(args: Array[String]): Unit = {
    val app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "BOARD GAMES ENGINE"
        val root = new Group
        scene = new Scene(root, 552, 552, Color.Green.brighter) {
            val label = new Label("CHOOSE A GAME TO START")
            label.setFont(Font.font("Impact", FontWeight.BOLD, 20))
            label.setLayoutX(160); label.setLayoutY(80)

            val imageXO = new Image(new FileInputStream("images/XO_StartMenu.png"))
            val imageViewXO = new ImageView(imageXO)
            val buttonXO = new Button(null, imageViewXO)
            imageViewXO.setFitWidth(100); imageViewXO.setFitHeight(100)
            buttonXO.setLayoutX(150); buttonXO.setLayoutY(250)

            val imageConnect4 = new Image(new FileInputStream("images/connect4_StartMenu.png"))
            val imageViewConnect4 = new ImageView(imageConnect4)
            val buttonConnect4 = new Button(null, imageViewConnect4)
            imageViewConnect4.setFitWidth(100); imageViewConnect4.setFitHeight(100)
            buttonConnect4.setLayoutX(270); buttonConnect4.setLayoutY(250)

            val imageChess = new Image(new FileInputStream("images/chess_StartMenu.png"))
            val imageViewChess = new ImageView(imageChess)
            val buttonChess = new Button(null, imageViewChess)
            imageViewChess.setFitWidth(100); imageViewChess.setFitHeight(100)
            buttonChess.setLayoutX(150); buttonChess.setLayoutY(140)

            val imageCheckers = new Image(new FileInputStream("images/checkers_StartMenu.png"))
            val imageViewCheckers = new ImageView(imageCheckers)
            val buttonCheckers = new Button(null, imageViewCheckers)
            imageViewCheckers.setFitWidth(100); imageViewCheckers.setFitHeight(100)
            buttonCheckers.setLayoutX(270); buttonCheckers.setLayoutY(140)

            buttonXO.onMouseClicked = e => {
              val canvas = new Canvas(300, 300)
              canvas.getGraphicsContext2D.fillRect(0, 0, 500, 500)
              content.setAll(canvas)
              Engine.selectGame(0, canvas);
            }
            buttonChess.onMouseClicked = e => {
              val canvas = new Canvas(552, 552)
              canvas.getGraphicsContext2D.fillRect(0, 0, 552, 552)
              content.setAll(canvas)
              Engine.selectGame(1, canvas);
            }
            buttonCheckers.onMouseClicked = e => {
              val canvas = new Canvas(552, 552)
              canvas.getGraphicsContext2D.fillRect(0, 0, 500, 500)
              content.setAll(canvas)
              Engine.selectGame(2,canvas);
            }
            buttonConnect4.onMouseClicked = e => {
              val canvas = new Canvas(552, 552)
              canvas.getGraphicsContext2D.fillRect(0, 0, 500, 500)
              content.setAll(canvas)
              Engine.selectGame(3, canvas);
            }

            content = List(label, buttonXO, buttonChess, buttonCheckers, buttonConnect4)
        }
      }
      stage.setMaxWidth(552)
      stage.setMinWidth(552)
      stage.setMinHeight(552)
      stage.setMaxHeight(552)
    }
    app.main(Array(""))
  }

}
