import org.scalatest.funsuite.AnyFunSuite

class AppTest extends AnyFunSuite with TraitSparkSessionTest {

  import sparkSession.implicits._

  test("this is sample test") {

    val rdd = sparkSession
      .sparkContext
      .parallelize(Seq("A", "B"))


    val ds = rdd.toDS

    assert(2 == ds.count()) // assertion
  }
}