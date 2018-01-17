lazy val root = project.in(file("."))
  .enablePlugins(GatlingPlugin)
  .settings(
    name := "gatling_examples",
    version := "0.1",
    scalaVersion := "2.12.3",
    scalacOptions ++= Seq(
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-unchecked",
      "-deprecation",
      "-Xfuture",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Ywarn-unused"
    ),
    javaOptions in Gatling := overrideDefaultJavaOptions("-Xms1024m", "-Xmx2048m"),
    resolvers += Resolver.typesafeRepo("releases"),
    libraryDependencies ++= Seq(
      "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.0",
      "io.gatling" % "gatling-test-framework" % "2.3.0"
    )
  )
