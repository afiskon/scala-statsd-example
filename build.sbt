name := "scala-statsd-example"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-Xmax-classfile-name", "100")

libraryDependencies ++= Seq(
    "com.timgroup" % "java-statsd-client" % "3.0.1"
  )

