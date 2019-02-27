package ttl.util.spark.udf

import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

import ttl.util.collection.Map._

class CombineMaps[K, V](keyType: DataType, valueType: DataType, merge: (V, V) => V) extends UserDefinedAggregateFunction {
  override def inputSchema: StructType = new StructType().add("map", dataType)
  override def bufferSchema: StructType = inputSchema
  override def dataType: DataType = MapType(keyType, valueType)
  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = buffer.update(0, Map[K, V]())

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer.update(0, buffer.getAs[Map[K, V]](0).combine(input.getAs[Map[K, V]](0), merge))
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = update(buffer1, buffer2)

  override def evaluate(buffer: Row): Any = buffer.getAs[Map[K, V]](0)
}
