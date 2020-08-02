
ThisBuild / scalaVersion := "2.13.0"
ThisBuild / sbtVersion := "1.3.8"

organization := "com.tooppoo"

lazy val root = (project in file(".")).settings(
  name := "try-tddbc",
)

lazy val sendai05 = (project in file("sendai05")).settings(
  name := "sendai05",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test",
  libraryDependencies += "org.scalatest" %% "scalatest-funspec" % "3.2.0" % "test"
)