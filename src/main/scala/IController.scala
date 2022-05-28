trait IController {
  def runGame(board: Array[Array[Char]], clicks: Array[(Int, Int)], player: Boolean): Boolean
  def init() : Array[Array[Char]]
  def getLogicalPos(pos: (Int, Int)) : (Int, Int)
  def gameMovesPieces() : Boolean
}
