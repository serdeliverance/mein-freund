import Dependencies._

ThisBuild / organization := "io.github.sdev.meinfreund"
ThisBuild / scalaVersion := "3.1.2"

lazy val beerCoin = (project in file("beer-coin"))
  .settings(
    name := "beer-coin"
  )

lazy val expenses = (project in file("expenses"))
  .settings(
    name := "expenses",
    libraryDependencies ++= Seq(
      catsEffect,
      catsEffectKernel,
      catsEffectStd,
      catsEffectTestingSpecs2 % Test,
      munitCatsEffect         % Test
    )
  )

lazy val notifications = (project in file("notifications"))
  .settings(
    name := "notifications"
  )

lazy val reportGenerator = (project in file("report-generator"))
  .settings(
    name := "report-generator"
  )
