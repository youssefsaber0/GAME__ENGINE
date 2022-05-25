package Checkers.controller


class CheckerController {
  def init() = {
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
      if (j > 7 && i < 8) {
        return checkEat(board, i + 1, 0,player)
      }
      if (i > 7 && j > 7) {
        return List();
      }
      if (eatMatcher(board, i, j)&&((player==0&&board(i)(j).isUpper)||(player==1&&board(i)(j).isLower))) {
            return (i * 8 + j :: checkEat(board, i, j + 1,player))
        }
       checkEat(board, i, j+1,player)
    }
  def eatMatcher(board:Array[Array[Char]],i:Int,j:Int)= board(i)(j) match {
    case 'k'=>eatKingCheck(board,i,j)
    case 'K'=>eatKingCheck(board,i,j)
    case 'p'=>eatNorthCheck(board,i,j)
    case 'P'=>eatSouthCheck(board,i,j)
    case _=>false
  }
  def eatNorthCheck(board: Array[Array[Char]], i: Int, j: Int): Boolean ={
    var flag=false
    if (i - 2 < 8) {
      if (j - 2 < 8) {
        if ((board(i)(j).isLower && !board(i-1)(j-1).isLower)||(board(i)(j).isLower && !board(i-1)(j-1).isLower)) {
          if (board(i - 2)(j - 2) == '-') {
            flag = true
          }
        }
      }
      if (j + 2 < 8) {
        if ((board(i)(j).isLower && !board(i-1)(j+1).isLower)||(board(i)(j).isLower && !board(i-1)(j+1).isLower)) {
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
              if (j - 2 < 8) {
                if ((board(i)(j).isLower && !board(i+1)(j-1).isLower)||(board(i)(j).isLower && !board(i+1)(j-1).isLower)) {
                  if (board(i + 2)(j - 2) == '-') {
                    flag = true
                  }
                }
              }
              if (j + 2 < 8) {
                if ((board(i)(j).isLower && !board(i+1)(j+1).isLower)||(board(i)(j).isLower && !board(i+1)(j+1).isLower)) {
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
      if (board(i - 1)(j - 1) == '-') {
        flag = true
      }
      if (board(i - 1)(j + 1) == '-')
        flag = true
    }
    flag
  }
  def southMovesV(board: Array[Array[Char]], i: Int, j: Int): Boolean ={
    var flag=false;
    if(i+1<=7) {
      if (board(i + 1)(j - 1) == '-')
        flag = true
      if (board(i + 1)(j + 1) == '-')
        flag = true
    }
    flag
  }
  def canMoveV(board: Array[Array[Char]], i: Int, j: Int): Boolean ={
      var flag =false
      if(board(i)(j)==' ')
        {
        }
     else if(board(i)(j).isUpper){
        flag=flag||northMovesV(board,i,j)
        flag=flag||southMovesV(board,i,j)
      }else{
        if(board(i)(j)==' '){
          flag=flag||northMovesV(board,i,j)
        }
        else{
          flag=flag||southMovesV(board,i,j)
        }
      }
      flag
    }
  def moveNorth(board: Array[Array[Char]], i: Int, j: Int,newI:Int,newJ:Int) ={
    var flag =false
    if(i-newI!=1||Math.abs(j-newJ)!=1||board(newI)(newJ)=='-'||board(i)(j)=='P') {}
    else if(board(newI)(newJ)=='-'){
      board(newI)(newJ) =board(i)(j)
      board(i)(j)='-'
      flag=true
    }
    flag
  }
  def moveSouth(board: Array[Array[Char]], i: Int, j: Int,newI:Int,newJ:Int) ={
    var flag =false
    if(newI-i!=1||Math.abs(j-newJ)!=1||board(newI)(newJ)=='-'||board(i)(j)=='p') {}
    else if(board(newI)(newJ)=='-'){
      board(newI)(newJ) =board(i)(j)
      board(i)(j)='-'
    }
    flag=true
  }
  def promote(board: Array[Array[Char]], i: Int, j: Int)= board(i)(j)match{
    case 'p' =>'k'
    case 'P'=>'K'
  }
  def valdiateIndex(i:Int,j:Int): Boolean ={
   i>=0&&i<8&&j>=0&&j<8
}
  def eatNorth(board: Array[Array[Char]], i: Int, j: Int,newI:Int,newJ:Int): Boolean ={
    var flag=false;
    if((board(i)(j)=='P')||(newI-i)!=2||(board(i-1)(j+1)!='-'&&board(i-1)(j-1)!='-')){
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
    if((board(i)(j)=='p')||(i-newJ)!=2||(board(i+1)(j+1)!='-'&&board(i+1)(j-1)!='-')){
      return false
    }
    if(i-2>=0) {
      if((newJ-j)==2){
        if((board(i)(j).isLower&&board(i+1)(j+1).isUpper||board(i)(j).isUpper&&board(i+1)(j+1).isLower)&&(board(i+1)(j+1)!='-')&&board(newI)(newJ)=='-'){
          board(i+1)(j+1)='-'
          board(newI)(newJ)=board(i)(j)
          board(i)(j)='-'
          flag=true
        }
      }
      else if((j-newJ)==2){
        if((board(i)(j).isLower&&board(i+1)(j-1).isUpper||board(i)(j).isUpper&&board(i+1)(j-1).isLower)&&(board(i+1)(j-1)!='-')&&board(newI)(newJ)=='-'){
          board(i+1)(j-1)='-'
          board(newI)(newJ)=board(i)(j)
          board(i)(j)='-'
          flag=true
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
    if((player==0&&board(i)(j).isLower||player==0&&board(i)(j).isUpper)||board(newI)(newJ)!='-'){
       false
    }
    else true
  }
  def game(board: Array[Array[Char]],i:Int,j:Int,newI:Int,newJ:Int,player:Int) :Boolean = {
    var board = init()
    if (!valdiateIndex(i, j) && (!valdiateIndex(i, j))) {
      return false;
    }

    if(!vadiateInput(board, i, j, newI, newJ, player)){
      return false
    }
    var canMove = checkEat(board, 0, 0, player)
    if (!canMove.isEmpty) {
      if (!canMove.contains(i * 8 + j)) {
        return false
      } else {
        return eatMove(board,i, j, newI, newJ)
      }
    }
       true
    }
}