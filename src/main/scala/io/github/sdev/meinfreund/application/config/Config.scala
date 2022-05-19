package io.github.sdev.meinfreund.application.config

object Config:

  case class AppConfig(db: DbConfig)

  case class DbConfig(host: String, port: Int, user: String, password: String, database: String, max: Int = 10)
