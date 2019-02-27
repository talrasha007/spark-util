import ttl.util.spark.udf._
import org.apache.spark.sql._
import org.apache.spark.sql.types._

object TestSparkCombinMaps {
  def main(args: Array[String]): Unit = {
    val ss = SparkSession.builder().master("local[1]").appName("test").getOrCreate()
    import ss.sqlContext.implicits._

    val input = ss.sparkContext.parallelize(Seq(
      (1, Map("t" -> 1, "e" -> 2)),
      (1, Map("e" -> 3, "s" -> 2)),
      (2, Map("s" -> 1, "vv" -> 2)),
      (2, Map("t" -> 1, "e" -> 2)),
      (3, Map("t" -> 1, "e" -> 2))
    )).toDF("id", "vmap")

    val combineMaps = new CombineMaps[String, Int](StringType, IntegerType, _ + _)
    input.groupBy("id").agg(combineMaps($"vmap")).show(truncate = false)
  }
}
