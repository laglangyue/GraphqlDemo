import Dependencies.Versions._

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

ThisBuild / resolvers ++= Seq(
  Resolver.mavenLocal,
  "Sonatype OSS Snapshots" at "https://s01.oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype OSS Releases" at "https://s01.oss.sonatype.org/content/repositories/releases"
)

ThisBuild / versionScheme := Some("semver-spec")

inThisBuild(
  List(
    scalaVersion           := scala3_Version,
    organization           := "demo.github.graphql",
    sonatypeCredentialHost := "oss.sonatype.org",
    sonatypeRepository     := "https://oss.sonatype.org/service/local",
    homepage               := Some(url("https://github.com/SymphonyQL")),
    licenses               := List("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")),
    developers             := List(
      Developer(
        id = "jxnu-liguobin",
        name = "jxnu-liguobin",
        email = "dreamylost@outlook.com",
        url = url("https://github.com/jxnu-liguobin")
      )
    )
  )
)

lazy val commonSettings =
  Seq(
    Test / fork   := true,
    run / fork    := true,
    scalaVersion  := scala3_Version,
    doc / sources := Seq(),
    javacOptions ++= Seq("-source", "21", "-encoding", "UTF-8"),
    scalacOptions ++= Seq(
      "-language:dynamics",
      "-explain",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-explain-types",
      "-Ykind-projector",
      "-language:higherKinds",
      "-language:existentials",
      "-Xfatal-warnings"
    ) ++ Seq("-Xmax-inlines", "100")
  )

lazy val SymphonyQL = (project in file("."))
  .aggregate(
    springQL
  )
  .settings(
    publish / skip := true,
    commonSettings,
    commands ++= Commands.value
  )

lazy val springQL = (project in file("springQl"))
  .settings(
    name := "springQl",
    publish / skip := true,
    commonSettings,
    libraryDependencies ++= Dependencies.dept.springQL
  )
