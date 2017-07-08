package com.hackx.spark

import java.util.Properties

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

/**
  * Created by 曹磊(Hackx) on 8/7/2017.
  * Email: caolei@mobike.com
  */
object HelloSparkSQL {
  def main(args: Array[String]): Unit = {
    val logger = LoggerFactory.getLogger("spark")
    val sparkSession = SparkSession.builder().appName("HelloSparkSQL").master("local").getOrCreate()
    operateJdbc(sparkSession)
    sparkSession.stop()
  }

  private def operateJdbc(sparkSession: SparkSession): Unit = {
    val connectionProperties = new Properties()
    connectionProperties.put("user", "root")
    connectionProperties.put("password", "root")
    val jdbcDF = sparkSession.read.jdbc("jdbc:mysql://localhost:3306", "badcoder.lljr_purchase_records", connectionProperties)
    jdbcDF.printSchema()
  }
}
