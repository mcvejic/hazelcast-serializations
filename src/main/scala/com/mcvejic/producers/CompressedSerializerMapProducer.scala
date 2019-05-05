package com.mcvejic.producers

import com.hazelcast.Scala._
import com.hazelcast.config._
import com.mcvejic.model.{CacheKey, CacheValue}
import com.mcvejic.serializers.CacheSerializers
import com.mcvejic._

object CompressedSerializerMapProducer {
  def main(args: Array[String]): Unit = {

    val conf = new Config
    conf.getSerializationConfig.addSerializerConfig(new SerializerConfig().setImplementation(CacheSerializers.CacheKey).setTypeClass(classOf[CacheKey]))
    conf.getSerializationConfig.addSerializerConfig(new SerializerConfig().setImplementation(CacheSerializers.CacheValue).setTypeClass(classOf[CacheValue]))
    serialization.Defaults.register(conf.getSerializationConfig)

    val hz = conf.newInstance()
    val map = hz.getMap[CacheKey, Set[CacheValue]]("compressed-map")
    val data = generateData(numOfEntries = 1000000)

    map.clear()

    println("Try #1, set")
    measureSet[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #2, set")
    measureSet[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #3, set:")
    measureSet[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #4, put all:")
    measurePutAll[CacheKey, Set[CacheValue]](map, data)
  }
}
