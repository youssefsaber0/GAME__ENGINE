class Checkers_Controller extends IController{
  def init(): Array[Array[Char]] = {
    var board = Array(
      Array('-', 'P', '-', 'P', '-', 'P', '-', 'P'),
      Array('P', '-', 'P', '-', 'P', '-', 'P', '-'),
      Array('-', 'P', '-', 'P', '-', 'P', '-', 'P'),
      Array('-', '-', '-', '-', '-', '-', '-', '-'),
      Array('-', '-', '-', '-', '-', '-', '-', '-'),
      Array('p', '-', 'p', '-', 'p', '-', 'p', '-'),
      Array('-', 'p', '-', 'p', '-', 'p', '-', 'p'),
      Array('p', '-', 'p', '-', 'p', '-', 'p', '-'),
    )
    for (i <- 0 to 7; j <- 0 to 7) {
      println(board(i)(j) + " i " + i + " J " + j)
    }
    board
  }
  def checkEat(board: Array[Array[Char]], i: Int, j: Int,player:Int): List[Int] = {
    if (i == 8 && j == 0) {
      return List();
    }
    if (j > 7 && i < 8) {
      return checkEat(board, i + 1, 0,player)
    }

    if (eatMatcher(board, i, j)&&((player==0&&board(i)(j).isUpper)||(player==1&&board(i)(j).isLower))) {
      return (i * 8 + j :: checkEat(board, i, j + 1,player))
    }
    checkEat(board, i, j+1,player)
  }
  def eatMatcher(board:Array[Array[Char]],i:Int,j:Int): Boolean = board(i)(j) match {
    case 'k'=>eatKingCheck(board,i,j)
    case 'K'=>eatKingCheck(board,i,j)
    case 'p'=>eatNorthCheck(board,i,j)
    case 'P'=>eatSouthCheck(board,i,j)
    case _=>false
  }
  def eatNorthCheck(board: Array[Array[Char]], i: Int, j: Int): Boolean ={
    var flag=false
    if (i - 2 >= 0) {
      if (j - 2 >= 0) {
        if(board(i-1)(j-1)!='-')
        if (((board(i)(j).isLower && board(i-1)(j-1).isUpper)||(board(i)(j).isUpper && board(i-1)(j-1).isLower))&&board(i-2)(j-2)=='-') {
          if (board(i - 2)(j - 2) == '-') {
            flag = true
          }
        }
      }
      if (j + 2 < 8) {
        if(board(i-1)(j+1)!='-')
        if (((board(i)(j).isLower && board(i-1)(j+1).isUpper)||(board(i)(j).isUpper && board(i-1)(j+1).isLower))&&board(i-2)(j+2)=='-') {
          if (board(i - 2)(j + 2) =='-') {
            flag = true
          }
        }
      }
    }
    flag
  }
  def eatSouthCheck(board: Array[Array[Char]], i: Int, j: Int): Boolean = {
    var flag = false;
    if (i + 2 < 8) {
      if (j - 2 >= 0) {
        if(board(i+1)(j-1)!='-')
          if (((board(i)(j).isLower && board(i+1)(j-1).isUpper)||(board(i)(j).isUpper && board(i+1)(j-1).isLower))&&board(i+2)(j-2)=='-') {
          if (board(i + 2)(j - 2) == '-') {
            flag = true
          }
        }
      }
      if (j + 2 < 8) {
        if(board(i+1)(j+1)!='-')
          if (((board(i)(j).isLower && board(i+1)(j+1).isUpper)||(board(i)(j).isUpper && board(i+1)(j+1).isLower))&&board(i+2)(j+2)=='-') {
          if (board(i + 2)(j + 2) == '-') {
            flag = true
          }
        }
      }
    }
    flag
  }
  def eatKingCheck(board:Array[Array[Char]],i:Int,j:Int):Boolean={
    var flag=false
    flag =flag||eatSouthCheck(board,i,j)
    flag =flag|| eatNorthCheck(board,i,j)
    flag
  }
  def northMovesV(board: Array[Array[Char]], i: Int, j: Int): Boolean ={
    var flag=false;
    if(i-1>=0) {
      if(j-1>=0) {
        if (board(i - 1)(j - 1) == '-') {
          flag = true
        }
      }
      if(j+1<8) {
        if (board(i - 1)(j + 1) == '-')
          flag = true
      }
    }
    flag
  }
  def southMovesV(board: Array[Array[Char]], i: Int, j: Int): Boolean ={
    var flag=false;
    if(i+1<=7) {
      if(j-1>=0) {
        if(i+1<8&&j-1>=0)
        if (board(i + 1)(j - 1) == '-')
          flag = true
      }
      if(i+1<8&&j+1<8)
        if (board(i + 1)(j + 1) == '-')
          flag = true
    }
    flag
  }
  def canMoveV(board: Array[Array[Char]], i: Int, j: Int): Boolean ={
    var flag =false
    if(board(i)(j)=='-')
    {
    }
    else if(board(i)(j)=='k'||board(i)(j)=='K'){
      flag=flag||northMovesV(board,i,j)
      flag=flag||southMovesV(board,i,j)
    }else{
      if(board(i)(j)=='p'){
        flag=flag||northMovesV(board,i,j)
      }
      else{
        flag=flag||southMovesV(board,i,j)
      }
    }
    flag
  }
  def moveNorth(board: Array[Array[Char]], i: Int, j: Int,newI:Int,newJ:Int): Boolean ={
    var flag =false
    if(i-newI!=1||Math.abs(j-newJ)!=1||board(newI)(newJ)!='-'||board(i)(j)=='P') {}
    else if(board(newI)(newJ)=='-'){
      board(newI)(newJ) =board(i)(j)
      board(i)(j)='-'
      flag=true
    }
    flag
  }
  def moveSouth(board: Array[Array[Char]], i: Int, j: Int,newI:Int,newJ:Int): Boolean={
    var flag =false
    if(newI-i!=1||Math.abs(j-newJ)!=1||board(newI)(newJ)!='-'||board(i)(j)=='p') {}
    else if(board(newI)(newJ)=='-'){
      board(newI)(newJ) =board(i)(j)
      board(i)(j)='-'
      flag=true
    }
    flag
  }
  def promote(board: Array[Array[Char]], i: Int, j: Int):Char= board(i)(j)match{
    case 'p' =>'k'
    case 'P'=>'K'
    case _=>board(i)(j)
  }
  def valdiateIndex(i:Int,j:Int): Boolean ={
    i>=0&&i<8&&j>=0&&j<8
  }
  def eatNorth(board: Array[Array[Char]], i: Int, j: Int,newI:Int,newJ:Int): Boolean ={
    var flag=false;
    if((board(i)(j)=='P'||(i-newI)!=2)){
      return false
    }
    if((newJ>j&&board(i-1)(j+1)=='-')||(newJ<j&&board(i-1)(j-1)=='-')){
      return false
    }
    if(i-2>=0) {
      if((newJ-j)==2){
        if((board(i)(j).isLower&&board(i-1)(j+1).isUpper||board(i)(j).isUpper&&board(i-1)(j+1).isLower)&&(board(i-1)(j+1)!='-')&&board(newI)(newJ)=='-'){
          board(i-1)(j+1)='-'
          board(newI)(newJ)=board(i)(j)
          board(i)(j)='-'
          flag=true
        }
      }
      else if((j-newJ)==2){
        if((board(i)(j).isLower&&board(i-1)(j-1).isUpper||board(i)(j).isUpper&&board(i-1)(j-1).isLower)&&(board(i-1)(j-1)!='-')&&board(newI)(newJ)=='-'){
          board(i-1)(j-1)='-'
          board(newI)(newJ)=board(i)(j)
          board(i)(j)='-'
          flag=true
        }
      }
    }
    flag
  }
  def eatSouth(board: Array[Array[Char]], i: Int, j: Int,newI:Int,newJ:Int): Boolean ={
    var flag=false
        if ((board(i)(j) == 'p') || (newI - i) != 2) {
          return false
        }

    if((newJ>j&&board(i+1)(j+1)=='-')||(newJ<j&&board(i+1)(j-1)=='-')){
      return false
    }
      if (i + 2 < 8) {
        if ((newJ - j) == 2) {
          if ((board(i)(j).isLower && board(i + 1)(j + 1).isUpper || board(i)(j).isUpper && board(i + 1)(j + 1).isLower) && (board(i + 1)(j + 1) != '-') && board(newI)(newJ) == '-') {
            board(i + 1)(j + 1) = '-'
            board(newI)(newJ) = board(i)(j)
            board(i)(j) = '-'
            flag = true
          }
        }
        else if ((j - newJ) == 2) {
          if ((board(i)(j).isLower && board(i + 1)(j - 1).isUpper || board(i)(j).isUpper && board(i + 1)(j - 1).isLower) && (board(i + 1)(j - 1) != '-') && board(newI)(newJ) == '-') {
            board(i + 1)(j - 1) = '-'
            board(newI)(newJ) = board(i)(j)
            board(i)(j) = '-'
            flag = true
          }
        }
      }
    flag
  }
  def eatMove(board: Array[Array[Char]], i: Int, j: Int,newI:Int,newJ:Int): Boolean ={
    var flag=false
    if(i-newI==2){
      flag=flag||eatNorth(board,i,j,newI,newJ)
    }
    else if(newI-i==2) {
      flag=flag||eatSouth(board,i,j,newI,newJ)
    }
    flag
  }
  def vadiateInput(board: Array[Array[Char]],i:Int,j:Int,newI:Int,newJ:Int,player:Int): Boolean ={
    println("hhh")
    println(player==0&&board(i)(j).isLower)
    println(player==1&&board(i)(j).isUpper)
    println(board(newI)(newJ)!='-')
    println("HH")
    println(player)
    if(((player==0&&(board(i)(j).isLower))||(player==1&&board(i)(j).isUpper))||board(newI)(newJ)!='-'){
      false
    }
    else true
  }
  def move(board: Array[Array[Char]],i:Int,j:Int,newI:Int,newJ:Int): Boolean ={
    var flag=false
    if('p'==board(i)(j)){
      flag=flag||moveNorth(board,i, j, newI, newJ)
    }
    else if('P'==board(i)(j))
      flag=flag||moveSouth(board, i, j, newI, newJ)
    else if(board(i)(j)!='-'){
      flag=flag||moveNorth(board,i, j, newI, newJ)
      flag=flag||moveSouth(board, i, j, newI, newJ)
    }
    flag
  }
  def reEat(board: Array[Array[Char]],i:Int,j:Int): String ={
    var flag="";
    if(board(i)(j)=='p'){
      if(eatMove(board,i,j,i-2,j-2)) {
        flag= "northW"
      }
      else if(eatMove(board,i,j,i-2,j+2)){
        flag= "northE"
      }
    }
    if(board(i)(j)=='P'){
      if(eatMove(board,i,j,i+2,j-2))
        flag= "southW"
      else if (eatMove(board,i,j,i+2,j+2)){
        flag= "southE"
      }
    }
    if(board(i)(j)=='k'||board(i)(j)=='K'){
      if(eatMove(board,i,j,i-2,j-2))
        flag= "northW"
      else if(eatMove(board,i,j,i-2,j+2))
        flag= "northE"
      else if(eatMove(board,i,j,i+2,j-2))
        flag= "southW"
      else if(eatMove(board,i,j,i+2,j+2))
        flag= "southE"

    }
    flag
  }
  def reEatNewi(string: String,i:Int):Int =string match{
    case "northW"=>i-2
    case "northE"=>i-2
    case "southW"=>i+2
    case "southE"=>i+2
  }
  def reEatNewj(string: String,j:Int):Int=string match{
    case "northW"=>j-2
    case "northE"=>j+2
    case "southW"=>j-2
    case "southE"=>j+2
  }
  def game(board: Array[Array[Char]],i:Int,j:Int,newI:Int,newJ:Int,player:Int) :Boolean = {
    var flag =false
    if (!valdiateIndex(i, j) && (!valdiateIndex(i, j))) {
      return false
    }
    if(!vadiateInput(board, i, j, newI, newJ, player)){
      println(i,j)
      println(newI,newJ)
      return false
    }
    var canEat = checkEat(board, 0, 0, player)
    canEat.foreach(println)
    if (!canEat.isEmpty) {
      if (!canEat.contains(i * 8 + j)) {
        return false
      } else {
        if( eatMove(board,i, j, newI, newJ)){
          var newReI=newI
          var newReJ=newJ
          while (eatMatcher(board, newReI, newReJ)){
            var string =reEat(board, newReI, newReJ)
            println(string)
            newReI=reEatNewi(string,newReI)
            newReJ=reEatNewj(string, newReJ)
          }
          if(newReI==0||newReI==7){
            board(newReI)(newReJ)=promote(board, newReI, newReJ)
          }
          return true
        }
      }

    }
    else if(canMoveV(board, i, j)) {
      if(move(board, i, j, newI, newJ)){
        if(newI==7||newI==0){
          board(newI)(newJ)=promote(board, newI, newJ)
        }
        return true
      }
    }
    flag
  }

  def runGame(board: Array[Array[Char]],clicks:Array[(Int,Int)],player:Boolean): Boolean ={
    var i=clicks(0)._1/69;
    var j=clicks(0)._2/69;
    var newI=clicks(1)._1/69;
    var newJ=clicks(1)._2/69;
    var intPlayer=0
    if(!player)
      intPlayer=1
     var flag =game(board,i,j,newI,newJ,intPlayer)
    flag
  }

  def getLogicalPos(pos: (Int, Int)) : (Int, Int) = {
    (pos._1/69, pos._2/69)
  }

  override def gameMovesPieces(): Boolean = true

}
