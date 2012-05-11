import sbt._
import Keys._

object Build extends Build {
    lazy val root = Project(id = "scala-lastfmapi",
                            base = file(".")) aggregate(lastfmapi, demoproject)

    lazy val lastfmapi = Project(id = "lastfmapi",
                           base = file("lastfmapi"))

    lazy val demoproject = Project(id = "demo-project",
                           base = file("demo-project"))
}