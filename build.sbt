name := "scala-playground"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.6.5",
  "org.typelevel" %% "cats" % "0.9.0",
  "org.typelevel" %% "cats-effect" % "0.3"
)