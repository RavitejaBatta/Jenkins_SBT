import org.scalatest.Assertions.assertThrows
import org.scalatest.funspec.AnyFunSpec

class SamplePersonTest extends AnyFunSpec {

  describe("a person") {
    describe("when being created") {

      it("should throw an exception if it's given a zero or negative age") {
        assertThrows[IllegalArgumentException] {
          Person("john", "doe", -10)
        }
      }

      it("correctly formats first and last names") {
        assertResult("John Doe") {
          Person("john", "doe", 10).getFullName
        }
      }
    }
  }
}