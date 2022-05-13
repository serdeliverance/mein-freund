package io.github.sdev.meinfreund.adapters.out.persistence

import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.application.util.PersistenceUtils.SessionPool
import io.github.sdev.meinfreund.domain.entities.Expense
import skunk.implicits._
import skunk.syntax.codec
import skunk.Command
import skunk.Codec

class ExpenseRepositoryImpl[F[_]](sessionPool: SessionPool[F]) extends ExpenseRepository[F]:

  // TODO
  override def save(expense: Expense): F[Expense] = ???

object SqlCodec:

  // TODO
  val codec: Codec[Expense] = ???

object Commands:

  // FIXME
  val insertCmd: Command[Expense] =
    sql"""
      INSERT INTO expense VALUES ($codec)
    """".command
