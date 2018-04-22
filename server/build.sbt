name := """rxjavatests-server"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  javaWs,
  guice,
  "com.typesafe.play" %% "play-json" % "2.6.7"
)
