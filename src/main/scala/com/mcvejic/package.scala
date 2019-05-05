package com

import com.mcvejic.model.{CacheKey, CacheValue}
import scala.collection.JavaConverters._

package object mcvejic {

  def generateData(numOfEntries: Int) : Map[CacheKey, Set[CacheValue]] = {
    val data = collection.mutable.Map[CacheKey, Set[CacheValue]]()
    (1 to numOfEntries).foreach(i => {
      var resultSet = Set[CacheValue]()
      (1 to 200).foreach(y => {
        resultSet = resultSet + CacheValue(i, i + 1)
      })
      data.put(CacheKey("env", "test", java.util.UUID.randomUUID.toString), resultSet)
    })

    data.toMap
  }

  def measurePut[A, B](map: java.util.Map[A, B], data: Map[A, B]): Unit = {
    val start = System.currentTimeMillis
    data.foreach(i => map.put(i._1, i._2))
    println(s"Total time: ${System.currentTimeMillis - start}")
  }

  def measureSet[A, B](map: com.hazelcast.core.IMap[A, B], data: Map[A, B]): Unit = {
    val start = System.currentTimeMillis
    data.foreach(i => map.set(i._1, i._2))
    println(s"Total time: ${System.currentTimeMillis - start}")
  }

  def measurePutAll[A, B](
                           map: java.util.Map[A, B],
                           data: Map[A, B]): Unit = {
    val start = System.currentTimeMillis
    map.putAll(data.asJava)
    println(s"Total time: ${System.currentTimeMillis - start}")
  }
}
