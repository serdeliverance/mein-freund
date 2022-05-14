package io.github.sdev.meinfreund.adapters.out.persistence.in.rest

import cats.effect.Sync
import io.github.sdev.meinfreund.domain.usecases.AddSingleExpenseUseCase
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

object ExpenseRoute:

  def endpoints[F[_]: Sync](addSingleExpenseUseCase: AddSingleExpenseUseCase[F]): HttpRoutes[F] =
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] { case req @ POST -> Root / "expenses" => Ok("ok") }
