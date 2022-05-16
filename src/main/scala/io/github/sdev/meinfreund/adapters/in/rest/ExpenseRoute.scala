package io.github.sdev.meinfreund.adapters.in.rest

import cats.effect.Sync
import io.github.sdev.meinfreund.domain.usecases.AddSingleExpenseUseCase
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.circe.CirceEntityDecoder._
import io.github.sdev.meinfreund.adapters.in.rest.json.SerDes._
import io.github.sdev.meinfreund.domain.entities.Expense

object ExpenseRoute:

  def endpoints[F[_]: Sync](addSingleExpenseUseCase: AddSingleExpenseUseCase[F]): HttpRoutes[F] =
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] { case req @ POST -> Root / "expenses" =>
      for
        expense <- req.as[Expense] // FIXME
        credit  <- addSingleExpenseUseCase.addExpense(expense)
        resp    <- credit.asJson
      yield Ok(resp)
    }
