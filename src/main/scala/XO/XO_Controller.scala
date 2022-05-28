class XO_Controller extends IController {

  override def runGame(board: Array[Array[Char]], clicks: Array[(Int, Int)], player: Boolean): Boolean = {
    val logicalPos = getLogicalPos(clicks(0)._1, clicks(0)._2)
    val correct = validate(board)(logicalPos._1, logicalPos._2)
    applyMove(board, getPiece(player))(logicalPos._1, logicalPos._2, correct)
    for(i <- 0 to 2; j <- 0 to 2) println(s"=> ${board(i)(j)}")
    correct
  }

  override def checkClicks(clicks: Array[(Int, Int)]): Boolean = {
    if(clicks.length != 1) false
    else true
  }

  override def init(): Array[Array[Char]] = {
    Array(Array(' ', ' ', ' '), Array(' ', ' ', ' '), Array(' ', ' ', ' '))
  }

  val validate: Array[Array[Char]] => (Int, Int) => Boolean = board =>{
    val isCellFree: (Int, Int) => Boolean = (x, y) => {
      if(x < 0 || y < 0 || x > 2 || y > 2 || board(x)(y) != ' ') false
      else true
    }
    isCellFree
  }

  def applyMove (board : Array[Array[Char]], p: Char) : (Int, Int, Boolean) => Unit = {
    val doMove: (Int, Int, Boolean) => Unit = (x, y, b) => {
      println(b)
      if(b) board(x)(y) = p }
    doMove
  }

  val getPiece : Boolean => Char = {
    case true => 'x'
    case false => 'o'
  }

  val getLogicalPos : (Int, Int) => (Int, Int) = (x,y) => {
    println(x, y)
    if(x < 55 || y < 55)  (-1, -1)
    else{
      val pos = ((x - 55) / 100, (y - 55) / 100)
      println(pos)
      pos
    }
  }

}
