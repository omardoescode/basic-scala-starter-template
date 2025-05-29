val scala3Version = "3.7.0"

enablePlugins(ScalafmtPlugin)
lazy val root = project
  .in(file("."))
  .settings(
    name := "interpreter_strategy",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "org.typelevel" %% "cats-core" % "2.13.0"
    )
  )
