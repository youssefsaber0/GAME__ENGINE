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

    if (isValidMove(board, col)) {
      applyMoveToBoard(board, col, player)
      return true
    }
    false
  }

  def getColIndexFromPosition(position: (Int, Int)): Int = {
    if (position._2 <= 16 || position._2 >= 536)
      return -1

    (position._2 - 16) / 75
  }

  def isValidMove(board: Array[Array[Char]], col: Int ): Boolean = {
    moveIndicesIsInBound(col) && !columnIsFull(board, col)
  }

  def moveIndicesIsInBound(col: Int): Boolean = {
    0 <= col && col <= 6
  }

  def columnIsFull(board: Array[Array[Char]], col: Int): Boolean = {
    for (r <- 0 to 5) {
      if (board(r)(col) == '-')
        return false
    }
    true
  }

  def applyMoveToBoard(board: Array[Array[Char]], col: Int, isRedTurn: Boolean): Array[Array[Char]]  = {
    var char: Char = if (isRedTurn) 'R' else 'Y'
    val r = getFirstFreeRowIndex(board, col)
    board(r)(col) = char

    board
  }

  def getFirstFreeRowIndex(board: Array[Array[Char]], col: Int): Int = {
    for (r <- 5 to 0 by -1) {
      if (board(r)(col) == '-')
        return r
    }
    -1
  }


  def printBoard(board : Array[Array[Char]]): Unit = {
    for (r <- 0 to 5) {
      for (c <- 0 to 6) {
        print(s"${board(r)(c)}  ")
      }
      println()
    }
  }

  def getLogicalPos(pos: (Int, Int)) : (Int, Int) = {
    if (pos._2 <= 16 || pos._2 >= 536)
      return (0, -1)

    (0, (pos._2 - 16) / 75)
  }
  override def gameMovesPieces(): Boolean = false

}
