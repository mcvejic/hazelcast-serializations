package com.mcvejic.consumers

import com.hazelcast.Scala._
import com.hazelcast.config._
import com.mcvejic.model.{CacheKey, CacheValue}
import info.jerrinot.subzero.SubZero

object SubZeroMapConsumer {
  def main(args: Array[String]): Unit = {
    val conf = new Config
    SubZero.useAsGlobalSerializer(conf)
    val hz = conf.newInstance()

    val map = hz.getMap[CacheKey, Set[CacheValue]]("sub-zero-map")

    while (true) {
      Thread.sleep(1000)
      println(s"Total size of sub-zero-map: ${map.size()}")
    }
  }
}
