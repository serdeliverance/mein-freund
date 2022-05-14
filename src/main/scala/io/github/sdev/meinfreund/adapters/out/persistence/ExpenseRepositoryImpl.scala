package io.github.sdev.meinfreund.adapters.out.persistence

import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.application.util.PersistenceUtils.SessionPool
import io.github.sdev.meinfreund.domain.entities.Expense
import skunk.implicits._
import skunk.syntax.codec
import skunk.Command
import skunk.Codec
import SqlCommands.insertExpense
import cats.effect.Resource
import skunk.Session
import cats.Applicative.AllOps

class ExpenseRepositoryImpl[F[_]](sessionPool: Resource[F, Session[F]]) extends ExpenseRepository[F]:

  // TODO
  override def save(expense: Expense): F[Unit] =
    sessionPool.use { session =>
      session.prepare(insertExpense).use { cmd =>
        cmd.execute(expense)
      }
    }
