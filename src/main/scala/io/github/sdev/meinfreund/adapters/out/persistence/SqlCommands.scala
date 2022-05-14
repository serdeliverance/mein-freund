package io.github.sdev.meinfreund.adapters.out.persistence

import skunk.Command
import skunk.implicits._
import io.github.sdev.meinfreund.domain.entities.Expense
import SqlCodecs.expenseCodec

object SqlCommands:
  val insertExpense: Command[Expense] =
    sql"""
        INSERT INTO expense VALUES ($expenseCodec)
        """".command
