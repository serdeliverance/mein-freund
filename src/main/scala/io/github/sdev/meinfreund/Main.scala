package io.github.sdev.meinfreund

import cats.effect.IOApp
import cats.effect.IO
import cats.effect.ExitCode
import cats.effect.Resource
import org.http4s.server.Server
import org.http4s.ember.server.EmberServerBuilder
import com.comcast.ip4s._
import org.http4s.server.Router
import org.http4s.implicits._
import io.github.sdev.meinfreund.adapters.in.rest.ExpenseRoute
import io.github.sdev.meinfreund.domain.usecases.AddSingleExpenseUseCase
import io.github.sdev.meinfreund.adapters.out.persistence.ExpenseRepositoryImpl
import io.github.sdev.meinfreund.adapters.out.quotation.QuotationProviderImpl

object Main extends IOApp:

  private def createServer(): Resource[IO, Server] =
    for
      sessionPool <- Resource.eval(???)
      expenseRepository              = new ExpenseRepositoryImpl(sessionPool)
      quotationProvider              = new QuotationProviderImpl[IO]
      addSingleExpenseUseCaseService = new AddSingleExpenseUseCase(quotationProvider, expenseRepository)
      expenseRoutes <- Resource.eval(ExpenseRoute.endpoints[IO](addSingleExpenseUseCaseService))
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

  def run(args: List[String]): IO[ExitCode] = ???
