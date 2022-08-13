import org.scalatest.funsuite.AnyFunSuite

class CubeCalculatorTest extends AnyFunSuite {
  test("CubeCalculator.cube") {
    assert(CubeCalculator.cube(3) === 27)
  }
  val calculator = CubeCalculator

  test("multiplication with 0 should always give 0") {
    assert(calculator.multiply(572389, 0) == 0)
    assert(calculator.multiply(-572389, 0) == 0)
    assert(calculator.multiply(0, 0) == 0)
  }

  test("dividing by 0 should throw a math error") {
    assertThrows[ArithmeticException](calculator.divide(57238, 0))
  }
  
}