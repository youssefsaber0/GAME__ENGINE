import Chess.Model.{Bishop, King, Knight, PawnBlack, PawnWhite, Queen, Rook}

class Chess_Controller extends IController {

  override def init(): Array[Array[Char]] = {
    var board = Array(
      Array('R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'),
      Array('P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'),
      Array('-', '-', '-', '-', '-', '-', '-', '-'),
      Array('-', '-', '-', '-', '-', '-', '-', '-'),
      Array('-', '-', '-', '-', '-', '-', '-', '-'),
      Array('-', '-', '-', '-', '-', '-', '-', '-'),
      Array('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'),
      Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'),
    )
    printBoard(board)
    board
  }

  override def checkClicks(clicks : Array[(Int,Int)]) : Boolean = {
    clicks.length==2
  }

  def validateInput(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int), player: Boolean): Boolean = {

    val sourceRow: Int = source._1
    val sourceColumn: Int = source._2
    val destinationRow: Int = destination._1
    val destinationColumn: Int = destination._2

    if(board(sourceRow)(sourceColumn) == '-'){
      println("Clicked on empty space.")
      return false
    }

    if(player){
      if(board(sourceRow)(sourceColumn).isUpper){
        println("Player 1 Tried to move Player 2's piece")
        return false
      }
      if(board(destinationRow)(destinationColumn).isLower){
        println("Player 1 released on his own pieces")
        return false
      }
    }
    else{
      if(board(sourceRow)(sourceColumn).isLower){
        println("Player 2 Tried to move Player 1's piece")
        return false
      }
      if(board(destinationRow)(destinationColumn).isUpper){
        println("Player 2 released on his own pieces")
        return false
      }
    }
    true
  }

  def checkMove(board : Array[Array[Char]], source : (Int, Int), destination : (Int, Int)): Boolean ={
    val sourceRow: Int = source._1
    val sourceColumn: Int = source._2
    val sourcePiece: Char = board(sourceRow)(sourceColumn)
    val mover = pieceMatch(sourcePiece)
    val isValidMove = mover(board, source, destination)
    isValidMove
  }

  def pieceMatch(sourcePiece : Char): (Array[Array[Char]], (Int, Int), (Int, Int)) => Boolean = sourcePiece match {
    case ('r' | 'R') => Rook.move

    case ('n' | 'N') => Knight.move

    case ('b' | 'B') => Bishop.move

    case ('q' | 'Q') => Queen.move

    case ('k' | 'K') => King.move

    case 'p' => PawnWhite.move

    case 'P' => PawnBlack.move
  }

  def makeMove(board: Array[Array[Char]], source: (Int, Int), destination: (Int, Int)): Unit = {
    board(destination._1)(destination._2) = board(source._1)(source._2)
    board(source._1)(source._2) = '-'
  }

  override def runGame(board : Array[Array[Char]],clicks : Array[(Int,Int)] , player: Boolean): Boolean = {
    val source  = map(clicks(0))
    val destination = map(clicks(1))
    if (validateInput(board,source,destination,player)
      &&  checkMove(board,source,destination)){
      makeMove(board,source,destination)
      return true;
    }
    false
  }

  def map(click : (Int,Int)): (Int,Int) ={
    (click._1/69,click._2/69)
  }

  def printBoard(board : Array[Array[Char]]) : Unit = {
    for (i <- 0 to 7) {
      for(j <- 0 to 7){
        print(board(i)(j) + " ")
      }
      println()
    }
  }

}

//object Main {
//  def main(args: Array[String]): Unit = {
//    val cont = new ChessController
//    cont.initializeBoard()
//    val pawnMove = cont.pieceMatch('p')
//    var board = Array(
//      Array('-', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'),
//      Array('p', 'P', 'P', 'P', 'P', 'P', 'P', 'P'),
//      Array('-', '-', '-', '-', '-', '-', '-', '-'),
//      Array('-', '-', '-', '-', '-', '-', '-', '-'),
//      Array('-', '-', '-', '-', '-', '-', '-', '-'),
//      Array('-', '-', '-', '-', '-', '-', '-', '-'),
//      Array('-', 'p', 'p', 'p', 'p', 'p', 'p', 'p'),
//      Array('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'),
//    )
//    pawnMove(board, (1, 0), (0, 0))
//    cont.printBoard(board)
//  }
//}
