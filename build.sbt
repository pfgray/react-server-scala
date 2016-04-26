organization := "org.example"

name := "hello"

version := "0.1-SNAPSHOT"

mainClass in assembly := Some("net.paulgray.react.Test")

libraryDependencies ++= Seq(
  "com.typesafe" %% "jse" % "1.1.2",
  "org.webjars" % "react" % "15.0.1"
)
