package io.github.sdev.meinfreund.application.config

import ciris._
import cats.implicits._
import cats.syntax.all._
import cats.effect.Async

object Config:

  case class AppConfig(db: DbConfig)

  case class DbConfig(host: String, port: Int, user: String, password: String, database: String, max: Int = 10)

  def loadConfig[F[_]: Async](): F[AppConfig] =
    (
      env("DB_HOST").as[String].default("localhost"),
      env("DB_PORT").as[Int].default(8080),
      env("DB_USER").as[String].default("root"),
      env("DB_PASSWORD").as[String].default("root"),
      env("DB_DATABASE").as[String].default("mf")
    ).parMapN { (host, port, user, password, database) =>
      val dbConfig = DbConfig(host, port, user, password, database)
      AppConfig(dbConfig)
    }.load[F]
