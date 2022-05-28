class Connect4_Controller extends IController{

  override def init(): Array[Array[Char]] = {
    val board = Array.ofDim[Char](6, 7)
    for (r <- 0 to 5; c <- 0 to 6) {
      board(r)(c) = '-'
    }
    board
  }

  override def runGame(board: Array[Array[Char]], clicks: Array[(Int, Int)], player: Boolean): Boolean = {
    val col = getColIndexFromPosition(clicks(0))
    print(col.toString())

    if (isValidMove(board, col)) {
      applyMoveToBoard(board, col, player)
      return true
    }
    false
  }

  def getColIndexFromPosition(position: (Int, Int)): Int = {
    if (position._1 < 16 || position._1 > 536)
        return -1
    (position._1 - 16) / 75
  }

  def isValidMove(board: Array[Array[Char]], col: Int ): Boolean = {
    moveIndicesIsInBound(col) && isBoardBlockEmpty(board, col) && !tokenIsFloating(board, col)
    true
  }

  def moveIndicesIsInBound(col: Int): Boolean = {
    0 < col && col < 6
  }

  def colH(board: Array[Array[Char]], block: (Int,Int)): Boolean = {
    board(block._1)(block._2) == '-'
  }

  def tokenIsFloating(board: Array[Array[Char]], indices: (Int,Int)): Boolean = {
    indices._1 == 0 || board(indices._1 - 1)(indices._2) != '-'
  }

  def applyMoveToBoard(board: Array[Array[Char]], move: (Int,Int), isRedTurn: Boolean): Array[Array[Char]]  = {
    var char: Char = if (isRedTurn) 'R' else 'Y'
    board(move._1)(move._2) = char
    board
  }

  override def checkClicks(clicks: Array[(Int, Int)]): Boolean = {
    clicks.length==2
  }

  def printBoard(board : Array[Array[Char]]): Unit = {
    for (r <- 0 to 5) {
      for (c <- 0 to 6) {
        print(s"${board(r)(c)}  ")
      }
      println()
    }
  }
}
