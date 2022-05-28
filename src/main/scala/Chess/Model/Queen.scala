package Chess.Model

object Queen {

  def move(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int)) : Boolean = {
    println("Entered Queen's move method")
    Rook.move(board, source, destination) || Bishop.move(board, source, destination)
  }

}
