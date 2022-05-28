package Chess.Model

object Bishop {

  def move(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int)) : Boolean = {
    println("Entered Bishop's move method")
    val sourceRow: Int = source._1
    val sourceColumn: Int = source._2
    val destinationRow: Int = destination._1
    val destinationColumn: Int = destination._2

    if(sourceRow == destinationRow || sourceColumn == destinationColumn){
      return false
    }

    if(sourceRow > destinationRow ){ // Up
      if(sourceColumn < destinationColumn) { // Up-Right
        var i = sourceRow - 1
        var j = sourceColumn + 1
        while ((i != destinationRow) && (j != destinationColumn)){
          if (board(i)(j) != '-'){
            return false
          }
          i-=1
          j+=1
        }
      }
      else { // Up-Left
        var i = sourceRow - 1
        var j = sourceColumn - 1
        while ((i != destinationRow) && (j != destinationColumn)){
          if (board(i)(j) != '-'){
            return false
          }
          i-=1
          j-=1
        }
      }
    }
    else{ // Down
      if(sourceColumn < destinationColumn) { // Down-Right
        var i = sourceRow + 1
        var j = sourceColumn + 1
        while ((i != destinationRow) && (j != destinationColumn)){
          if (board(i)(j) != '-'){
            return false
          }
          i+=1
          j+=1
        }
      }
      else { // Down-Left
        var i = sourceRow + 1
        var j = sourceColumn - 1
        while ((i != destinationRow) && (j != destinationColumn)){
          if (board(i)(j) != '-'){
            return false
          }
          i+=1
          j-=1
        }
      }
    }
    true
  }
}
