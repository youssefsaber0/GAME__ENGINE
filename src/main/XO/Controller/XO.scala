package XO.Controller

import XO.model.tail

class XO {

  def intialize()={
     var board=Array.fill(3) {
      Array.fill(3) {
        new tail()
      }
    }
    board
  }
  def play( board:Array[Array[tail]],i:Int,j:Int){

  }
}