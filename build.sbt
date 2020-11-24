val Http4sVersion  = "0.21.11"
val CirceVersion   = "0.13.0"
val DoobieVersion  = "0.9.2"
val Specs2Version  = "4.1.0"
val LogbackVersion = "1.2.3"
val Uuid4sVersion  = "0.1.5"

lazy val root = (project in file("."))
  .settings(
    organization := "solutions.eta",
    name := "comicbookviewer0",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.11",
    libraryDependencies ++= Seq(
      "org.http4s"    %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"    %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s"    %% "http4s-circe"        % Http4sVersion,
      "org.http4s"    %% "http4s-dsl"          % Http4sVersion,
      "io.circe"      %% "circe-generic"       % CirceVersion,
      "org.tpolecat"  %% "doobie-core"         % DoobieVersion,
      "org.tpolecat"  %% "doobie-h2"           % DoobieVersion,
      "org.pure4s"    %% "uuid4s"              % Uuid4sVersion,
      "org.specs2"    %% "specs2-core"         % Specs2Version % "test",
      "ch.qos.logback" % "logback-classic"     % LogbackVersion
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3"),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings"
)
