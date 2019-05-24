import sbt._

object Dependencies {

  object Version {
    val Circe = "0.10.0"
    val Cats = "1.6.0"
    val CatsEffect = "1.2.0"
    val Slinky = "0.4.3"
    val ScalaJsBinary = "0.6"
    val Http4sVersion = "0.19.0"
    val LogbackVersion = "1.2.3"
    val sjsSuffix = s"_sjs${Version.ScalaJsBinary}"
  }

  val Backend: Seq[ModuleID] =
    Seq(
      "org.http4s" %% "http4s-blaze-server" % Version.Http4sVersion,
      "org.http4s" %% "http4s-circe" % Version.Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Version.Http4sVersion,
      "ch.qos.logback" % "logback-classic" % Version.LogbackVersion,
      "org.typelevel" %% s"cats-core" % Version.Cats,
      "org.typelevel" %% s"cats-macros" % Version.Cats,
      "org.typelevel" %% s"cats-kernel" % Version.Cats,
      "org.typelevel" %% "cats-effect" % Version.CatsEffect,
      "org.http4s" %% "http4s-circe" % Version.Http4sVersion,
    )

  val SharedJVM: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-core" % Version.Circe,
    "io.circe" %% "circe-generic-extras" % Version.Circe,
    "io.circe" %% "circe-parser" % Version.Circe,
    "io.circe" %% "circe-java8" % Version.Circe
  )

  val SharedJS: Seq[ModuleID] = Seq(
    "io.circe" %% s"circe-core${Version.sjsSuffix}"           % Version.Circe,
    "io.circe" %% s"circe-scalajs${Version.sjsSuffix}"        % Version.Circe,
    "io.circe" %% s"circe-generic-extras${Version.sjsSuffix}" % Version.Circe,
    "io.circe" %% s"circe-parser${Version.sjsSuffix}"         % Version.Circe
  )


  val Frontend: Seq[ModuleID] =
    Seq(
      "me.shadaj" %% s"slinky-web${Version.sjsSuffix}" % Version.Slinky,
      "me.shadaj" %% s"slinky-hot${Version.sjsSuffix}" % Version.Slinky,
      "org.typelevel" %% s"cats-core${Version.sjsSuffix}" % Version.Cats,
      "org.typelevel" %% s"cats-macros${Version.sjsSuffix}" % Version.Cats,
      "org.typelevel" %% s"cats-kernel${Version.sjsSuffix}" % Version.Cats,
      "org.typelevel" %% s"cats-effect${Version.sjsSuffix}" % Version.CatsEffect,
      "com.lihaoyi" %% s"utest${Version.sjsSuffix}" % "0.6.3" % Test // can't use ScalaTest because of this: https://github.com/scalatest/scalatest/issues/873
    )

  val FrontendNpm = Seq(
    "react" → "16.4.1",
    "react-dom" → "16.4.0",
    "react-proxy" → "1.1.8",
    "react-redux" → "5.0.7",
    "redux" → "4.0.0",
  )

  val FrontendDevNpm = Seq(
    "url-loader" → "1.0.1",
    "react-router-dom" → "4.3.1",
    "style-loader" → "0.20.3",
    "css-loader" → "0.28.11",
    "html-webpack-plugin" → "3.2.0",
    "copy-webpack-plugin" → "4.5.1",
    "webpack-merge" → "4.1.2",
    "node-fetch" -> "2.2.0",
    "gettext-parser" -> "2.0.0",
    "@babel/runtime" -> "*"
  )
}
