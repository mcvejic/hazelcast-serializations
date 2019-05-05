package com.mcvejic.serializers

import com.hazelcast.Scala.serialization.Defaults
import com.hazelcast.Scala.serialization.lz4.CompressedSerializers
import com.hazelcast.nio.{ObjectDataInput, ObjectDataOutput}
import com.mcvejic.model.{CacheKey, CacheValue}

object CacheSerializers extends CompressedSerializers(Defaults) {
  val CacheKey =  new StreamCompressor[CacheKey](High) {
    def compress(out: ObjectDataOutput, cacheKey: CacheKey): Unit = {
      out.writeUTF(cacheKey.env)
      out.writeUTF(cacheKey.name)
      out.writeUTF(cacheKey.key)
    }
    def inflate(inp: ObjectDataInput): CacheKey = {
      new CacheKey(inp.readUTF, inp.readUTF, inp.readUTF)
    }
  }

  val CacheValue = new StreamCompressor[CacheValue](High) {
    def compress(out: ObjectDataOutput, result: CacheValue): Unit = {
      out.writeInt(result.value)
      out.writeInt(result.value2)
    }
    def inflate(inp: ObjectDataInput): CacheValue = {
      new CacheValue(inp.readInt, inp.readInt)
    }
  }
}