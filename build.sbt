ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .settings(
    name := "Jenkins_SBT"
  )


libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"

