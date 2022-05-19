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
import io.github.sdev.meinfreund.domain.usecases.AddSingleExpenseUseCase
import io.github.sdev.meinfreund.application.config.Config.AppConfig
import io.github.sdev.meinfreund.application.config.Config.DbConfig
import io.github.sdev.meinfreund.adapters.out.persistence.ExpenseRepositoryImpl
import io.github.sdev.meinfreund.adapters.out.quotation.QuotationProviderImpl
import skunk.Session
import cats.effect.Async
import natchez.Trace.Implicits.noop
import skunk.implicits._

object Main extends IOApp:

  private def createServer[F[_]: Async](): Resource[F, Server] =
    for
      config <- Resource.eval(
        AppConfig(DbConfig("localhost", 8080, "root", "root", "mf")).pure[F]
      ) // TODO load config using ciris
      sessionPool <- Session.pooled[F](
        host = config.db.host,
        port = config.db.port,
        user = config.db.user,
        password = Some(config.db.password),
        database = config.db.database,
        max = config.db.max
      ) // FIXME
      expenseRepository              = new ExpenseRepositoryImpl(sessionPool)
      quotationProvider              = new QuotationProviderImpl[F]
      addSingleExpenseUseCaseService = new AddSingleExpenseUseCase(quotationProvider, expenseRepository)
      expenseRoutes <- Resource.eval(ExpenseRoute.endpoints[F](addSingleExpenseUseCaseService))
      httpApp <- Resource.eval(
        Router("/v1/api" -> expenseRoutes).orNotFound
      )
      server <- EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(httpApp)
        .build
    yield server

  def run(args: List[String]): IO[ExitCode] =
    createServer[IO]().use(_ => IO.never).as(ExitCode.Success)
