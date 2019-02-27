package tal.util.collection

object Map {
  implicit class Combine[K, V](m: scala.collection.Map[K, V]) {
    def combine(o: scala.collection.Map[K, V], merge: (V, V) => V): scala.collection.Map[K, V] = {
      m ++ o.map { case (k,v) => k -> m.get(k).map(merge(v, _)).getOrElse(v) }
    }
  }
}