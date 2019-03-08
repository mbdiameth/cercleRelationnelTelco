package com.test
import java.io.File
import com.test.core.Geol
import org.apache.spark.{SparkConf, SparkContext}
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession
import org.slf4j.{Logger, LoggerFactory}

object TestApp extends App {

  val _configFile = ConfigFactory.parseFile(new File("src/main/resources/test.conf"))
  val _log: Logger = LoggerFactory.getLogger(TestApp.getClass)

  val _spark : SparkSession = SparkSession.
    builder().
    master("local[*]").
    appName(_configFile.getString("TEST.APPS.NAME")).
    getOrCreate()

    val geo = Geol.csvFile()
    geo.show(15)
    //geo.write.options(Map("inferSchema" -> "true", "sep" -> ",", "header" -> "true")).csv("output/geo")
    val geoKPI = Geol.computeKPI()
    Console.println(geoKPI.show(20))

}
