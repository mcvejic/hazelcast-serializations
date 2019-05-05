package com.mcvejic.producers

import com.hazelcast.Scala._
import com.hazelcast.config._
import com.mcvejic.model.{CacheKey, CacheValue}
import com.mcvejic.{generateData, measurePutAll, measureSet}
import info.jerrinot.subzero.SubZero

object SubZeroMapProducer {
  def main(args: Array[String]): Unit = {

    val conf = new Config
    SubZero.useAsGlobalSerializer(conf)
    val hz = conf.newInstance()

    val map = hz.getMap[CacheKey, Set[CacheValue]]("sub-zero-map")
    val data = generateData(numOfEntries = 1000000)

    map.clear()

    println("Try #1, set:")
    measureSet[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #2, set:")
    measureSet[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #3, set:")
    measureSet[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #4, put all:")
    measurePutAll[CacheKey, Set[CacheValue]](map, data)
  }
}
