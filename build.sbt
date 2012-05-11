name := "scala-lastfmapi"

organization := "de.burnynet"

version := "0.1"

scalaVersion := "2.9.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.7.2" % "test"

libraryDependencies ++= Seq(
  "net.databinder" %% "dispatch-http" % "0.8.8"
)

resolvers += DefaultMavenRepository

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
