//import javafx.application.Application
//import javafx.scene.{Group, Scene}
//import javafx.scene.image.{Image, ImageView}
//import javafx.stage.Stage
//
//import java.io.FileInputStream
//
//class Test extends Application {
//  override def start(primaryStage: Stage) ={
//    primaryStage.setTitle("Sup!")
//
//    var inputstream = new FileInputStream("D:\\work\\ParadigmsProject\\image\\Board.png");
//    var image = new Image(inputstream);
//    var imageView = new ImageView(image);
//    val root = new Group(imageView)
//    imageView.setPreserveRatio(true);
//    val scene = new Scene(root, 600, 500)
//    primaryStage.setScene(new Scene(root, 300, 300))
//    primaryStage.show()
//  }
//}
//
//object Main {
//  def main(args: Array[String]) {
//    Application.launch(classOf[Test], args: _*)
//  }
//}


import Checkers.controller.CheckerController
import Checkers.view.Drawer
import javafx.application.Application
import javafx.scene.canvas.{Canvas, GraphicsContext}
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.scene.{Group, Scene}
import javafx.stage.Stage;
class ImageExample extends Application {
  //  @Override
  ///*    define win_height  645 //552+132
  //  define win_width   828 //552+276
  //  //one square's length:
  //  define slot 69
  //  */
  //  override def start(primaryStage: Stage) ={
  //    //Creating an image
  //    var image = new Image(new FileInputStream("D:\\work\\ParadigmsProject\\image\\Board1.png"));
  //
  //    //Setting the image view
  //    var imageView = new ImageView(image);
  //
  //    //Setting the position of the image
  //    imageView.setX(0);
  //    imageView.setY(0);
  //
  //    //setting the fit height and width of the image view
  //    imageView.setFitHeight(552);
  //    imageView.setFitWidth(552);
  //
  //    //Setting the preserve ratio of the image view
  //    imageView.setPreserveRatio(true);
  //    val imgChina = new Image ("D:\\work\\ParadigmsProject\\image\\BLACK.png");
  //    var ch = new ImageView(imgChina);
  //    //Setting the position of the image
  //    ch.setX(4);
  //    ch.setY(4);
  //
  //    //setting the fit height and width of the image view
  //    ch.setFitHeight(60);
  //    ch.setFitWidth(60);
  //    //Creating a Group object
  //    var root = new Group(imageView,ch);
  //
  //    //Creating a scene object
  //    var scene = new Scene(root, 552, 552);
  //
  //
  //    //Setting title to the Stage
  //    primaryStage.setTitle("Loading an image");
  //
  //    //Adding scene to the stage
  //    primaryStage.setScene(scene);
  //
  //    //Displaying the contents of the stage
  //    primaryStage.show();
  //  }
  def start(theStage:Stage)=
  {
    theStage.setTitle("Drawing Operations Test")
    val root = new Group()
    val canvas = new Canvas (552, 552)
    val gc  = canvas.getGraphicsContext2D
    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (event: MouseEvent) => {
      def foo(event: MouseEvent) = {
//        val imgChina = new Image (System.getProperty("user.dir")+"\\image\\CHECKER\\BKING.png");
//        println(System.getProperty("user.dir")+"\\image\\BKING.png")
//        gc.drawImage(imgChina,(event.getX/69).toInt*69+4,(event.getY/69).toInt*69+4,60,60)
//        println("("+event.getY+","+ event.getX+")")
//
//        println((event.getY/69).toInt, (event.getX/69).toInt)
val Drawer=new Drawer()
        val board=new CheckerController()

        Drawer.drawGame(gc,board.game(board.init()));
      }
      foo(event)
    })
    drawShapes(gc)
    root.getChildren.add(canvas)
    theStage.setScene(new Scene(root))
    theStage.show();
  }
  def drawShapes(gc:GraphicsContext): Unit ={
    val black = new Image (System.getProperty("user.dir")+"\\image\\CHECKER\\BKING.png");
    var board = new Image(System.getProperty("user.dir")+"\\image\\CHECKER\\board.png");

    gc.drawImage(board,0,0,552,552)

  }
}
object Main {
  def main(args: Array[String]) {
    Application.launch(classOf[ImageExample], args: _*)
  }
}