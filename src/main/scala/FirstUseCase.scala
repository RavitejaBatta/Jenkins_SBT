object FirstUseCase  extends App{


  def add2Num(a:Int)={

      if (a < 10){
        a + 3
      } else if (a > 10 && a < 20){
        a + 30
      }
      else 1000
  }




}
