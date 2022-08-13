import org.scalatest.Assertions.assertThrows
import org.scalatest.funsuite.AnyFunSuite

class SampleFunSuite extends AnyFunSuite {

  val lst = List.empty[String]
  val arr = Array("foo", "bar")

  test("test list methods") {
    assert(lst.size == 0)
  }

  test("test array methods") {
    assert(arr(0) == "foo")
    assertThrows[ArrayIndexOutOfBoundsException] {
      arr(13)
    }
  }
}