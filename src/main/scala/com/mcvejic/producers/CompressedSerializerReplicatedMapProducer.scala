package com.mcvejic.producers

import com.hazelcast.Scala._
import com.hazelcast.config._
import com.mcvejic.model.{CacheKey, CacheValue}
import com.mcvejic.serializers.CacheSerializers
import com.mcvejic.{generateData, measurePut, measurePutAll}

object CompressedSerializerReplicatedMapProducer {
  def main(args: Array[String]): Unit = {

    val conf = new Config
    conf.getSerializationConfig.addSerializerConfig(new SerializerConfig().setImplementation(CacheSerializers.CacheKey).setTypeClass(classOf[CacheKey]))
    conf.getSerializationConfig.addSerializerConfig(new SerializerConfig().setImplementation(CacheSerializers.CacheValue).setTypeClass(classOf[CacheValue]))
    serialization.Defaults.register(conf.getSerializationConfig)

    val hz = conf.newInstance()
    val map = hz.getReplicatedMap[CacheKey, Set[CacheValue]]("compressed-replicated-map")
    val data = generateData(numOfEntries = 1000000)

    map.clear()

    println("Try #1, put")
    measurePut[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #2, put")
    measurePut[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #3, put:")
    measurePut[CacheKey, Set[CacheValue]](map, data)
    map.clear()

    println("Try #4, put all:")
    measurePutAll[CacheKey, Set[CacheValue]](map, data)
  }
}
