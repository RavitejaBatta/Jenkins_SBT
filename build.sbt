ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.8"


lazy val root = (project in file("."))
  .settings(
      name := "Jenkins_SBT",
      scalastyleFailOnError := false
  )
lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

// scalastyle >= 0.9.0
compileScalastyle := scalastyle.in(Compile).toTask("").value
// scalastyle <= 0.8.0
compileScalastyle := scalastyle.in(Compile).toTask("").value

(Compile / compile) := ((Compile / compile) dependsOn compileScalastyle).value

val sparkVersion = "2.4.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"
libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "org.sonarsource.scanner.api" % "sonar-scanner-api" % "2.16.1.361" % Compile
// sbt clean coverage test
// export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)


import sbtsonar.SonarPlugin.autoImport.sonarProperties

sonarProperties := Map(
"sonar.host.url" -> "http://localhost:9000",
"sonar.projectName" -> "Project Name",
"sonar.projectKey" -> "project-name",
"sonar.sources" -> "src/main/scala",
"sonar.tests" -> "src/test/scala",
"sonar.junit.reportPaths" -> "target/test-reports",
"sonar.sourceEncoding" -> "UTF-8",
"sonar.language" -> "scala",
"sonar.scala.scoverage.reportPath" -> "target/scala-2.12/scoverage-report/scoverage.xml"

)