package com.test.core
import java.io.File

import com.test.TestApp
import com.test.TestApp.main
import org.apache.spark.sql.{DataFrame, SparkSession}
object Geol {
  val path = TestApp._configFile.getString("TEST.SOURCES.GEOL.PATH")

  def csvFile(): DataFrame = {
    //val filePath = _configFile.getString("TEST.SOURCES.GEOL.PATH")
    //val filePath = "src/main/resources/geolocation.csv"
    val df = TestApp._spark.read.options(Map("inferSchema" -> "true", "sep" -> ",", "header" -> "true")).csv(path)
    df.selectExpr("truckid", "driverid", "event", "city", "state", "velocity", "event_ind", "idling_ind")
  }

  def computeKPI() : DataFrame = {
    csvFile().createOrReplaceTempView("csvFilePop")
    val query =
      s"""
         |SELECT truckid, driverid, event, city, state, sum(cast(velocity as Int)) as velocity, sum(cast(event_ind as Int)) as event_ind,
         |sum(cast(idling_ind as Int)) as idling_ind
         |FROM csvFilePop
         |GROUP BY truckid, driverid, event, city, state
       """.stripMargin
    TestApp._spark.sql(query)

  }
}
