import org.scalatest.Assertions._

class MainTest extends App {

  // will pass
  assert("foo" == "foo")

  // will fail  
  assertResult("foo") {
    "bar".toUpperCase
  }

}