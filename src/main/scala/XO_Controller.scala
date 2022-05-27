class XO_Controller {
  var board : List[Char] = List(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')

  def Init(): Unit ={
    val drawer : XO_Drawer = new XO_Drawer()
    drawer.draw(board, 'A')
  }

  val play: (Int, Int, Char) => (List[Char], Char) = (x, y, z) => {
      if (validateMove (x, y)) registerMove(x, y, z)
      else (board, z)
  }

  val switchTurn : Char => Char = {
    case 'A' => 'B'
    case 'B' => 'A'
  }

  val validateMove: (Int, Int) => Boolean = (x, y) => {
    if(x < 0 || y < 0 || board(getListIndex(x)(y)) == 'x'|| board(getListIndex(x)(y)) == 'o') false
    else true
  }

  val registerMove: (Int, Int, Char) => (List[Char], Char) = (x, y, z) =>{
    val index = getListIndex(x)(y)
    if(z == 'A') board = board.updated(index, 'x')
    else if(z == 'B') board = board.updated(index, 'o')
    (board, switchTurn(z))
  }

  val getListIndex: Int => Int => Int = x => {
    val get2ndIndex: Int => Int = j => 3 * x + j
    get2ndIndex
  }
}
