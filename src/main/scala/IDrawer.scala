import scalafx.scene.canvas.{Canvas, GraphicsContext}

trait IDrawer {
  def draw(gc: GraphicsContext, board: Array[Array[Char]]): Unit
}
