package Chess.Model

import javax.swing.JOptionPane

object PawnWhite {


  def move(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int)) : Boolean = {
    println("Entered White Pawn's move method")
    val sourceRow: Int = source._1
    val sourceColumn: Int = source._2
    val destinationRow: Int = destination._1
    val destinationColumn: Int = destination._2

    var isValidMove = false

    if (destinationColumn == sourceColumn && destinationRow - sourceRow == -1 && board(destinationRow)(destinationColumn) == '-')
      isValidMove = true
    else if (sourceRow == 6 && destinationColumn == sourceColumn && destinationRow - sourceRow == -2 &&
      board(destinationRow)(destinationColumn) == '-' &&  board(destinationRow + 1)(destinationColumn) == '-')
      isValidMove = true
    else if (Math.abs(destinationColumn - sourceColumn) == 1 && destinationRow - sourceRow == -1 && board(destinationRow)(destinationColumn) != '-')
      isValidMove = true

    if (!isValidMove)
      return false

    if (sourceRow == 1 && destinationRow == 0) {
      val newPiece: Char = getPromotionChar()
      board(sourceRow)(sourceColumn) = newPiece
    }
    true
  }

  def getPromotionChar(): Char ={
    val array = Array("Queen", "Bishop", "Rook", "Knight")
    var res =  JOptionPane.showInputDialog(null, "Choose your piece",
      "Promotion", -1, null,  array.asInstanceOf[Array[Object]], array(0))
    res match  {
      case "Queen" => 'q'
      case "Bishop" => 'b'
      case "Rook" => 'r'
      case "Knight" => 'n'
    }
  }

}
