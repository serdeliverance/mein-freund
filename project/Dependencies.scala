import sbt._

object Dependencies {

  object V {
    val catsEffect              = "3.3.11"
    val catsEffectTestingSpecs2 = "1.4.0"
    val munitCatsEffect3        = "1.0.7"
    val mockitoCore             = "4.5.1"
    val mockitoScala            = "1.16.42"

    val organizeImports = "0.6.0"
  }

  val catsEffect              = "org.typelevel"        %% "cats-effect"                % V.catsEffect
  val catsEffectKernel        = "org.typelevel"        %% "cats-effect-kernel"         % V.catsEffect
  val catsEffectStd           = "org.typelevel"        %% "cats-effect-std"            % V.catsEffect
  val catsEffectTestingSpecs2 = "org.typelevel"        %% "cats-effect-testing-specs2" % V.catsEffectTestingSpecs2
  val munitCatsEffect         = "org.typelevel"        %% "munit-cats-effect-3"        % V.munitCatsEffect3
  val mockitoCore             = "org.mockito"           % "mockito-core"               % V.mockitoCore
  val mockitoScala            = "org.mockito"          %% "mockito-scala"              % V.mockitoScala
  val organizeImports         = "com.github.liancheng" %% "organize-imports"           % V.organizeImports
}
