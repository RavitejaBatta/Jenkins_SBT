import org.junit.Test
import junit.framework.TestCase
import org.junit.Assert._

class ToppingTests {

  @Test
  def foo {
    val t1 = Topping("cheese")
    val t2 = Topping("cheese")
    assertEquals(t1, t2)
  }

}