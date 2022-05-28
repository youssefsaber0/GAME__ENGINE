trait IController {
  def runGame(board: Array[Array[Char]], clicks: Array[(Int, Int)], player: Boolean): Boolean
  def checkClicks(clicks:Array[(Int, Int)]): Boolean
  def init() : Array[Array[Char]]
}
