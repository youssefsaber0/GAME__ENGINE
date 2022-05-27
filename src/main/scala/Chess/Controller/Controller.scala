package Chess.Controller

class Controller {

  def initializeBoard() = {
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
    for (i <- 0 to 7) {
      for(j <- 0 to 7){
        print(board(i)(j) + " ")
      }
      println()
    }
    board
  }



}

object Main {
  def main(args: Array[String]): Unit = {
    val cont = new Controller
    cont.initializeBoard()
  }
}
