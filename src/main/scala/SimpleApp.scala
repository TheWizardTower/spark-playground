/* SimpleApp.scala */
import org.apache.spark.sql.SparkSession

object SimpleApp {
  def main(args: Array[String]): Unit = {
    var logFile = "/tmp/Nextcloud-crash.log"
    val spark: SparkSession = SparkSession.builder()
      .appName("spark-video-course")
      .master("local[*]")
      .getOrCreate()

    val df = spark.read
      .option("header", value = true)
      .option("inferSchema", value = true)
      .csv("data/AAPL.csv")

    df.show()
    df.printSchema()

    spark.stop()
  }
}
