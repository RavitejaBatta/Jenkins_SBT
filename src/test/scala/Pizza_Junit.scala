
import org.junit.Test
import junit.framework.TestCase
import org.junit.Assert._

class Pizza_Junit extends TestCase {

  var pizza: Pizza = _

  override def setUp {
    pizza = new Pizza
  }

  def testOneTopping {
    pizza.addTopping(Topping("green olives"))
    assertEquals(pizza.getToppings.size, 1)
  }

  def testAddingAndRemovingToppings {
    pizza.addTopping(Topping("green olives"))
    pizza.removeTopping(Topping("green olives"))
    assertEquals(pizza.getToppings.size, 0)
  }

}