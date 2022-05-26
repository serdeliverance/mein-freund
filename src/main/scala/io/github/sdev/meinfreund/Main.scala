package io.github.sdev.meinfreund

import cats.effect.IOApp
import cats.effect.IO
import cats.effect.ExitCode
import cats.effect.Resource
import cats.syntax.all._
import org.http4s.server.Server
import org.http4s.ember.server.EmberServerBuilder
import com.comcast.ip4s._
import org.http4s.server.Router
import org.http4s.implicits._
import io.github.sdev.meinfreund.adapters.in.rest.ExpenseRoute
import io.github.sdev.meinfreund.application.ports.in.AddSingleExpenseUseCase
import io.github.sdev.meinfreund.application.config.Config.AppConfig
import io.github.sdev.meinfreund.application.config.Config.DbConfig
import io.github.sdev.meinfreund.adapters.out.persistence.ExpenseRepositoryImpl
import io.github.sdev.meinfreund.adapters.out.quotation.QuotationProviderImpl
import skunk.Session
import cats.effect.Async
import cats.effect.std.Console
import natchez.Trace.Implicits.noop
import skunk.implicits._
import io.github.sdev.meinfreund.application.AddSingleExpenseService
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import org.http4s.server.middleware.{ Logger => HttpLogger }
import io.github.sdev.meinfreund.application.config.Config.loadConfig

object Main extends IOApp:

  private def createServer[F[_]: Async: Console](): Resource[F, Server] =

    given logger: Logger[F] = Slf4jLogger.getLogger[F]

    for
      config <- Resource.eval(loadConfig[F]())
      sessionPool <- Session.pooled[F](
        host = config.db.host,
        port = config.db.port,
        user = config.db.user,
        password = Some(config.db.password),
        database = config.db.database,
        max = config.db.max
      )
      expenseRepository   = new ExpenseRepositoryImpl(sessionPool)
      quotationProvider   = new QuotationProviderImpl[F]
      addSingleExpenseUse = AddSingleExpenseService.make(quotationProvider, expenseRepository)
      httpApp             = Router("/v1/api" -> ExpenseRoute.endpoints[F](addSingleExpenseUse)).orNotFound
      finalHttpApp        = HttpLogger.httpApp(true, true)(httpApp)
      server <- EmberServerBuilder
        .default[F]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(finalHttpApp)
        .build
    yield server

  def run(args: List[String]): IO[ExitCode] =
    createServer[IO]().use(_ => IO.never).as(ExitCode.Success)
