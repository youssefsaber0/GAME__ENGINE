package XO.model

class model {
  import Player._
  def valdiate(board:Array[Array[tail]],i:Int ,j:Int,player:CurrentPlayer.type ):Boolean={

    board(i)(j).state==XO_state.Empty&&(!outOfBoard(i, j))
  }
  def changePlayer(currentPlayer:CurrentPlayer.type)={
      if(currentPlayer.player==Player.player1){
        currentPlayer.player= player2
      }
      else
        currentPlayer.player=player1
  }
  def outOfBoard(i: Int, j: Int): Boolean = i >= 3 || j >= 3 || i < 0 || j < 0
def applyMove(board:Array[Array[tail]],i:Int,j:Int,player :CurrentPlayer.type): Unit ={
    if(player==player2){
      board(i)(j).state=XO_state.O
    }
  if(player==player2){
    board(i)(j).state=XO_state.X
  }

}
  def doMoveAndCheckturn(valdiate: (Array[Array[tail]],Int ,Int,CurrentPlayer.type)=>Boolean,board:Array[Array[tail]],i:Int,j:Int,player:CurrentPlayer.type): Unit ={
    if(valdiate(board,i,j,player)){
      val nextPlayer=changePlayer(player)
    }
    false
  }
}
