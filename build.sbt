name := "test"

version := "0.1"

scalaVersion := "2.11.8"
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies ++= Seq (
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.scala-logging" %% "scala-logging-api" % "2.1.2",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "org.apache.spark" %% "spark-sql" % "2.3.2"

)