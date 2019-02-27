name := "spark-util"

version := "0.1"

scalaVersion := "2.11.8"

updateOptions := updateOptions.value.withCachedResolution(true)

val sparkVersion = "2.1.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
)