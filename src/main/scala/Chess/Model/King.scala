package Chess.Model

object King {

  def move(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int)) : Boolean = {
    println("Entered King's move method")
    val sourceRow: Int = source._1
    val sourceColumn: Int = source._2
    val destinationRow: Int = destination._1
    val destinationColumn: Int = destination._2

    if (Math.abs(destinationColumn - sourceColumn) != 1) {
      if (Math.abs(destinationRow - sourceRow) == 1 || Math.abs(destinationRow - sourceRow) == 0)
        return true
    }
    else if (destinationColumn - destinationRow == 0){
      if (Math.abs(destinationRow - sourceRow) == 1)
        return true
    }
    false
  }

}
