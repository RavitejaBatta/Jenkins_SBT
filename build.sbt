ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.8"



lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")
lazy val testScalastyle = taskKey[Unit]("testScalastyle")
lazy val root = (project in file("."))
  .settings(fork in run := true)
  .settings(connectInput in run := true)
  .settings(
      name := "Jenkins_SBT",
      scalastyleFailOnError := false,
      compileScalastyle := scalastyle.in(Compile).toTask("").value,
      (compile in Compile) := ((compile in Compile) dependsOn compileScalastyle).value,
      testScalastyle := scalastyle.in(Test).toTask("").value,
      (test in Test) := ((test in Test) dependsOn testScalastyle).value)

val sparkVersion = "2.4.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"
libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion

// sbt clean coverage test
// export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)