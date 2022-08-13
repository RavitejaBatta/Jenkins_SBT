import org.scalatest.freespec.AnyFreeSpec

class Person_Test extends AnyFreeSpec{
  "fullName" - {
    "returns the first name and last name concatenated" in {
      val lilXan = Person_1("Lil", "Xan")
      assert(lilXan.fullName() === "Lil Xan")
    }
  }
}