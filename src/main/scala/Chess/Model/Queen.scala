package Chess.Model

object Queen {

  def move(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int)) : Boolean = {

    Rook.move(board, source, destination) &&
      Bishop.move(board, source, destination)
  }

}
