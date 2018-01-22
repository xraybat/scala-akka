name := "system"

organization := "au.com.carringbushsw"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.2"

scalacOptions ++= Seq("-deprecation")
scalacOptions ++= Seq("-feature")

//resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.12
libraryDependencies ++= Seq(
  "au.com.carringbushsw" %% "common" % "1.0-SNAPSHOT",
  "com.typesafe.akka" %% "akka-actor" % "2.5.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.2" % Test,
  "com.typesafe.akka" %% "akka-remote" % "2.5.2",
  "org.scalatest" % "scalatest_2.12" % "3.0.1"
)

mappings in packageBin in Compile +=
  (baseDirectory.value / "LICENSE") -> "SYSTEM-LICENSE"