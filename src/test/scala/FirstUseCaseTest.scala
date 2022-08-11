import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class FirstUseCaseTest extends AnyFlatSpec with should.Matchers{

  "My add num function" should "add 3" in  {
    FirstUseCase.add2Num(4) should be  (7)
  }
  "My add num function" should "return 44" in {
    FirstUseCase.add2Num(14) should be (44)
  }
  //"My add num function" should "return 1000" in {
  //  FirstUseCase.add2Num(55) should be (1000)
  //}
}
