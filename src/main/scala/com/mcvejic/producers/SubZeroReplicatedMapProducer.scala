package com.mcvejic.producers

import com.hazelcast.Scala._
import com.hazelcast.config._
import com.mcvejic.model.{CacheKey, CacheValue}
import com.mcvejic.{generateData, measurePut, measurePutAll}
import info.jerrinot.subzero.SubZero

object SubZeroReplicatedMapProducer {
  def main(args: Array[String]): Unit = {

    val conf = new Config
    SubZero.useAsGlobalSerializer(conf)
    val hz = conf.newInstance()

    val map = hz.getReplicatedMap[CacheKey, Set[CacheValue]]("sub-zero-replicated-map")
    val data = generateData(numOfEntries = 1000000)

    map.clear()

    println("Try #1, put:")
    measurePut[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #2, put:")
    measurePut[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #3, put:")
    measurePut[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #4, put all:")
    measurePutAll[CacheKey, Set[CacheValue]](map, data)
  }
}
