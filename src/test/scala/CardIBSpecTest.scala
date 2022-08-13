import  org.scalatest.funspec.AnyFunSpec

class CardIBSpecTest extends AnyFunSpec {
  describe("realName") {
    it("returns her birth name") {
      assert(CardiB.realName() === "Belcalis Almanzar")
    }
  }
  describe("iLike") {
    it("works with a single argument") {
      assert(CardiB.iLike("dollars") === "I like dollars")
    }
    it("works with multiple arguments") {
      assert(CardiB.iLike("dollars", "diamonds") === "I like dollars, diamonds")
    }
  }
}