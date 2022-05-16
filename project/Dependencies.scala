import sbt._

object Dependencies {

  object V {
    val catsEffect              = "3.3.11"
    val catsEffectTestingSpecs2 = "1.4.0"
    val munitCatsEffect3        = "1.0.7"
    val circe                   = "0.14.1"
    val skunk                   = "0.2.3"
    val http4sVersion           = "0.23.11"
    val mockitoCore             = "4.5.1"
    val mockitoScala            = "1.16.42"
    val logbackVersion          = "1.2.10"
    val log4catsVersion         = "2.2.0"

    val organizeImports = "0.6.0"
  }

  val catsEffect              = "org.typelevel"        %% "cats-effect"                % V.catsEffect
  val catsEffectKernel        = "org.typelevel"        %% "cats-effect-kernel"         % V.catsEffect
  val catsEffectStd           = "org.typelevel"        %% "cats-effect-std"            % V.catsEffect
  val catsEffectTestingSpecs2 = "org.typelevel"        %% "cats-effect-testing-specs2" % V.catsEffectTestingSpecs2
  val circeCore               = "io.circe"             %% "circe-core"                 % V.circe
  val circeParser             = "io.circe"             %% "circe-parser"               % V.circe
  val circeGeneric            = "io.circe"             %% "circe-generic"              % V.circe
  val http4sEmberServer       = "org.http4s"           %% "http4s-ember-server"        % V.http4sVersion
  val http4sCirce             = "org.http4s"           %% "http4s-circe"               % V.http4sVersion
  val http4sDsl               = "org.http4s"           %% "http4s-dsl"                 % V.http4sVersion
  val skunk                   = "org.tpolecat"         %% "skunk-core"                 % V.skunk
  val munitCatsEffect         = "org.typelevel"        %% "munit-cats-effect-3"        % V.munitCatsEffect3
  val mockitoCore             = "org.mockito"           % "mockito-core"               % V.mockitoCore
  val mockitoScala            = "org.mockito"          %% "mockito-scala"              % V.mockitoScala
  val logbackClassic          = "ch.qos.logback"        % "logback-classic"            % V.logbackVersion
  val log4cats                = "org.typelevel"        %% "log4cats-slf4j"             % V.log4catsVersion
  val organizeImports         = "com.github.liancheng" %% "organize-imports"           % V.organizeImports
}
