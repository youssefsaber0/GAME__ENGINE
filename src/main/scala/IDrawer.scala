import scalafx.scene.canvas.{Canvas, GraphicsContext}

trait IDrawer {
  def draw(gc: GraphicsContext, board: Array[Array[Char]]): Unit
  def movePieceToCursor(gc: GraphicsContext, board: Array[Array[Char]], selectedPieceIndex: (Int,Int), cursor: (Int,Int)): Unit
}
