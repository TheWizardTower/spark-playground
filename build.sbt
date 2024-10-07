val scala2Version = "2.12.20"
val scala3Version = "3.5.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Simple Project",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala2Version,

    Compile / run / fork := true,
    javaOptions ++= List("--add-exports", "java.base/sun.nio.ch=ALL-UNNAMED"),

    libraryDependencies ++= Seq(
         "org.scalameta" %% "munit" % "1.0.0" % Test,
         "org.apache.spark" %% "spark-sql" % "3.5.3",
    ),
  )
