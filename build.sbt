organization := "com.urbanandco"

name := "berlintime"

version := "0.1"

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false

// Main Java class for "sbt run"
mainClass in (Compile, run) := Some("com.urbanandco.berlintime.Main")

// Package Description
description := "Simple Lib to render Berlin Time"

// Compatability with maven
publishMavenStyle := true

libraryDependencies ++= Seq(
	"junit" % "junit" % "4.12" % "test",
	"org.slf4j" % "slf4j-api" % "1.7.12",
    "com.novocode" % "junit-interface" % "0.11" % "test"
)