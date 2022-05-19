import Dependencies._

ThisBuild / organization := "io.github.sdev.meinfreund"
ThisBuild / scalaVersion := "3.1.2"

lazy val root = (project in file("."))
  .settings(
    name := "mein-freund",
    libraryDependencies ++= Seq(
      catsEffect,
      catsEffectKernel,
      catsEffectStd,
      circeGeneric,
      http4sEmberServer,
      http4sCirce,
      http4sDsl,
      skunk,
      ciris,
      log4cats,
      logbackClassic                               % Runtime,
      mockitoCore                                  % Test,
      mockitoScala.cross(CrossVersion.for3Use2_13) % Test,
      catsEffectTestingSpecs2                      % Test,
      munitCatsEffect                              % Test
    )
  )
