import ttl.util.collection.Map._

object TestMap {
  def main(args: Array[String]): Unit = {
    val m1 = Map(1 -> 3, 3 -> 4)
    val m2 = Map(3 -> 1, 4 -> 2)
    m1.combine(m2, _ + _).foreach(println)
  }
}
