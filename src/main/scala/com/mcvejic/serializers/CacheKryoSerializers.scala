package com.mcvejic.serializers

import com.hazelcast.Scala.serialization.kryo.KryoSerializers
import com.hazelcast.Scala.serialization.lz4.FastStreamCompression
import com.mcvejic.model.{CacheKey, CacheValue}

object CacheKryoSerializers extends KryoSerializers {
      val CacheKeySer = new KryoStreamSerializer[CacheKey] with FastStreamCompression[CacheKey]
      val CacheValueSer = new KryoStreamSerializer[CacheValue] with FastStreamCompression[CacheValue]
}
