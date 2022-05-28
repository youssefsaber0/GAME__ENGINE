package Chess.Model

object Knight {

  def move(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int)) : Boolean = {
    println("Entered Knight's move method")
    val sourceRow: Int = source._1
    val sourceColumn: Int = source._2
    val destinationRow: Int = destination._1
    val destinationColumn: Int = destination._2

    if (Math.abs(destinationColumn - sourceColumn) == 1)
      return Math.abs(destinationRow - sourceRow) == 2

    else if (Math.abs(destinationColumn - sourceColumn) == 2)
      return Math.abs(destinationRow - sourceRow) == 1

    false
  }
}
