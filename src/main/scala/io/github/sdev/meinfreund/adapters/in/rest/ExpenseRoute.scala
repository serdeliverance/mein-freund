package io.github.sdev.meinfreund.adapters.in.rest

import cats.syntax.all._
import cats.effect.Concurrent
import io.github.sdev.meinfreund.domain.usecases.AddSingleExpenseUseCase
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import io.github.sdev.meinfreund.adapters.in.rest.json.SerDes.given
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.circe.CirceEntityDecoder._
import io.github.sdev.meinfreund.domain.entities.Expense
import io.github.sdev.meinfreund.domain.entities.OriginalExpense

object ExpenseRoute:

  def endpoints[F[_]: Concurrent](addSingleExpenseUseCase: AddSingleExpenseUseCase[F]): HttpRoutes[F] =
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] { case req @ POST -> Root / "expenses" =>
      for
        originalExpense <- req.as[OriginalExpense]
        credit          <- addSingleExpenseUseCase.addExpense(originalExpense)
        resp            <- Ok(credit.pure[F])
      yield resp

    }
