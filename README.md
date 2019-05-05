# hazelcast-serializations
Testing performance of hazelcast distributed cache with different serializations

Idea behind this test is to check what is write performance of hazelcast distributed cache, as ReplicatedMap or IMap when utilizing 
different serializations. Usually cache is not used for storing primitive data types, often we want to store more complex data, and in 
that case serialization plays really important role in cache performance.

Running:

Start one or more consumers under separate JVM (number of nodes influence performance of cache) by running:
sbt "runMain com.mcvejic.consumers.SubZeroReplicatedMapConsumer"

then start producer:
sbt "runMain com.mcvejic.producers.SubZeroReplicatedMapProducer"

