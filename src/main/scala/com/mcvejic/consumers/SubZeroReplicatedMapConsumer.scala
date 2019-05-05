package com.mcvejic.consumers

import com.hazelcast.Scala._
import com.hazelcast.config._
import com.mcvejic.model.{CacheKey, CacheValue}
import com.mcvejic.{generateData, measurePut, measurePutAll}
import info.jerrinot.subzero.SubZero

object SubZeroReplicatedMapConsumer {
  def main(args: Array[String]): Unit = {
    val conf = new Config
    SubZero.useAsGlobalSerializer(conf)
    val hz = conf.newInstance()

    val map = hz.getReplicatedMap[CacheKey, Set[CacheValue]]("sub-zero-replicated-map")

    while(true) {
      Thread.sleep(1000)
      println(s"Total size of sub-zero-replicated-map: ${map.size()}")
    }
  }
}
