package Chess.Model

object Rook {

  def move(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int)) : Boolean = {
    println("Entered Rook's move method")
    val sourceRow: Int = source._1
    val sourceColumn: Int = source._2
    val destinationRow: Int = destination._1
    val destinationColumn: Int = destination._2

    if(sourceRow != destinationRow && sourceColumn != destinationColumn)
      return false

    if(sourceColumn == destinationColumn){ // Vertical Movement
      if(sourceRow < destinationRow){ //Downwards
        for(i <- sourceRow+1 until destinationRow ){
          if(board(i)(sourceColumn) != '-'){
            return false
          }
        }
      }
      else { // Upwards
        for(i <- sourceRow-1 until destinationRow by -1) {
          if (board(i)(sourceColumn) != '-') {
            return false
          }
        }
      }
    }
    else{ // Horizontal Movement
      if(sourceColumn < destinationColumn){ //Right
        for(i <- sourceColumn+1 until destinationColumn ){
          if(board(sourceRow)(i) != '-'){
            return false
          }
        }
      }
      else { // Left
        for(i <- sourceColumn-1 until destinationColumn by -1) {
          if (board(sourceRow)(i) != '-') {
            return false
          }
        }
      }
    }
    true
  }

}
