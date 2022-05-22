package io.github.sdev.meinfreund.domain.entities

import java.time.LocalDateTime

// TODO add receipt path url
case class OriginalExpense(
    name: String,
    description: Option[String],
    category: Option[Category],
    amount: BigDecimal
)

object OriginalExpense:

  extension (originalExpense: OriginalExpense)
    def toExpense(quote: BigDecimal): Expense =
      Expense(
        name = originalExpense.name,
        description = originalExpense.description,
        category = originalExpense.category,
        date = LocalDateTime.now,
        amountUsd = originalExpense.amount * quote,
        amountArs = originalExpense.amount
      )
