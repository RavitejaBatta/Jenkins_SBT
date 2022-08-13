import org.scalatest.propspec.AnyPropSpec

class CalculatorPropSpec extends AnyPropSpec {
  val calculator = CubeCalculator

  val multiplyByZeroExamples = List((653278, 0), (-653278, 0), (0, 0))

  property("Calculator multiply by 0 should be 0") {
    assert(multiplyByZeroExamples.forall{
      case (a, b) => calculator.multiply(a, b) == 0
    })
  }

  property("Calculator divide by 0 should throw some math error") {
    assertThrows[ArithmeticException](calculator.divide(653278, 0))
  }
}