/* SimpleApp.scala */
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions.col

object SimpleApp {
  def main(args: Array[String]): Unit = {
    var logFile = "/tmp/Nextcloud-crash.log"
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark-video-course")
      .master("local[*]")
      .getOrCreate()

    val df: DataFrame = spark.read
      .option("header", value = true)
      .option("inferSchema", value = true)
      .csv("data/AAPL.csv")

    df.show()
    df.printSchema()

    df.select("Date", "Open", "Close").show()
    val dateColumn = df("Date")
    col("Date")
    import spark.implicits._
    $"Date"

    df.select(col("Date"), $"Open", df("Close")).show()

    val column = df("Open")
    val newColumn = (column + (2.0)).as("Open incleased by two")
    val columnString = column.cast(StringType).as("Open as String")

    df.select(column, newColumn, columnString).show()

    df.select(column, newColumn, columnString)
      .filter(newColumn > 2.0)
      .filter(newColumn > column)
      .filter(newColumn === column)
      .show()

    spark.stop()
  }
}
