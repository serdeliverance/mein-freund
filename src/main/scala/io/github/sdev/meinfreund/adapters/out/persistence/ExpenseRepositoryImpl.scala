package io.github.sdev.meinfreund.adapters.out.persistence

import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.domain.entities.Expense
import skunk.implicits._
import SqlCommands.insertExpense
import cats.effect.Resource
import skunk._
import cats.syntax.all._
import cats.Monad
import cats.effect._
import cats.Applicative
import cats.effect.implicits._
import cats.effect.MonadCancel

class ExpenseRepositoryImpl[F[_]](sessionPool: Resource[F, Session[F]])(using mc: MonadCancel[F, Throwable])
    extends ExpenseRepository[F]:

  override def save(expense: Expense): F[Unit] =
    sessionPool
      .use { session =>
        session.prepare(insertExpense).use { cmd =>
          cmd.execute(expense).void
        }
      }
