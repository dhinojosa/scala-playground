name := "scala-playground"

version := "1.0"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-native" % "3.6.9",
  "org.scalatest" %% "scalatest-funspec" % "3.2.0" % "test",
  "com.typesafe" % "config" % "1.3.3",
  "org.scalatest" %% "scalatest" % "3.2.0" % "test",
  "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0"
)

mainClass in assembly := Some("com.evolutionnext.config.ConfigApp")
