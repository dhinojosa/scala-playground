package com.evolutionnext.config

import com.typesafe.config.{Config, ConfigFactory}

object ConfigApp extends App{
   val config = ConfigFactory.load()
   val s = config.getString("ingestion.process.value")
   println(s)
}
