import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

enablePlugins(ScalaJSBundlerPlugin)

name := "scalajs-talk"

scalaVersion := "2.12.8"

lazy val backend =
  project
    .settings(
      name := "backend",
      version := "1.0",
      libraryDependencies ++= Dependencies.Backend,
      mainClass in assembly := Some("com.nihirash.talk.Main"),
    )
    .dependsOn(sharedJVM)

lazy val common =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Full)
    .jvmSettings(libraryDependencies ++= Dependencies.SharedJVM)
    .jsSettings(libraryDependencies ++= Dependencies.SharedJS)

lazy val sharedJS = common.js
lazy val sharedJVM = common.jvm

lazy val frontend =
  project
    .settings(Compile / npmDependencies ++= Dependencies.FrontendNpm)
    .settings(Compile / npmDevDependencies ++= Dependencies.FrontendDevNpm)
    .settings(libraryDependencies ++= Dependencies.Frontend)
    .settings(frontendCompileOptions)
    .settings(webpackSettings)
    .settings(scalaJSSettings)
    .enablePlugins(ScalaJSBundlerPlugin)
    .dependsOn(sharedJS)

lazy val frontendCompileOptions = Seq(
    scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M11" cross CrossVersion.full)
)

lazy val webpackSettings = Seq(
    fullOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack-opt.config.js"),
    fastOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack-fastopt.config.js"),
    Test / webpackConfigFile := Some(baseDirectory.value / "webpack-core.config.js"),
    fastOptJS / webpackDevServerExtraArgs := Seq("--inline", "--hot"),
    fullOptJS / webpackBundlingMode := BundlingMode.LibraryOnly(),
    fastOptJS / webpackBundlingMode := BundlingMode.LibraryOnly(),
    webpack / version := "4.5.0",
    startWebpackDevServer / version := "3.1.3"
)

lazy val scalaJSSettings = Seq(
    emitSourceMaps := true,
    scalaJSModuleKind := ModuleKind.CommonJSModule,
    moduleName in fullOptJS := "frontend",
    testFrameworks += new TestFramework("utest.runner.Framework"),
    Test / scalaJSStage := (sys.props.get("scalaJSStage") match {
        case Some("FullOpt") ⇒ FullOptStage
        case _ ⇒ FastOptStage
    })
)

