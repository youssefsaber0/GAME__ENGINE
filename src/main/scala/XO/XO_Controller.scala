class XO_Controller extends IController {
  override def runGame(board: Array[Array[Char]], clicks: Array[(Int, Int)], player: Boolean): Boolean = ???

  override def checkClicks(clicks: Array[(Int, Int)]): Boolean = ???

  override def init(): Array[Array[Char]] = ???
}
