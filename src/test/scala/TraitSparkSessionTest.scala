import org.apache.spark.sql.SparkSession

trait TraitSparkSessionTest {

  protected val sparkSession = SparkSession
    .builder()
    .appName("sample-spark-scala-project")
    .master("local[2]")
    .getOrCreate()
}


