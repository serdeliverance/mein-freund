package io.github.sdev.meinfreund.domain.entities

import java.time.LocalTime
import java.time.LocalDateTime

object CurrencyConverter:

  // TODO look a better way to do this on Scala 3 (maybe some context function)
  def convert(expense: OriginalExpense, quote: BigDecimal): Expense =
    Expense(
      name = expense.name,
      description = expense.description,
      category = expense.category,
      date = LocalDateTime.now,
      amountUsd = expense.amount * quote,
      amountArs = expense.amount
    )
