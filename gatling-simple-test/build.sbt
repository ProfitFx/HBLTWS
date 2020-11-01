name := "gatling-simple-test"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies += "io.gatling" % "gatling-test-framework" % "3.4.1"
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.4.1"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2"
enablePlugins(GatlingPlugin)
