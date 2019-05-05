import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "com/mcvejic"

lazy val root = (project in file("."))
  .settings(
    name := "Hazelcast test",
    resolvers += Resolver.jcenterRepo,
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.hazelcast" %% "hazelcast-scala" % "3.9.0" withSources(),
    // https://mvnrepository.com/artifact/com.hazelcast/hazelcast
    libraryDependencies += "com.hazelcast" % "hazelcast" % "3.12",
    libraryDependencies += "net.jpountz.lz4" % "lz4" % "1.3.0",
    // https://mvnrepository.com/artifact/com.esotericsoftware/kryo
    libraryDependencies += "com.esotericsoftware" % "kryo" % "4.0.2",
    libraryDependencies += "info.jerrinot" % "subzero-all" % "0.9"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
