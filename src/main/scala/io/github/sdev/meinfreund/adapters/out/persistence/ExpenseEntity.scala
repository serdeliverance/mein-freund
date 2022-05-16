package io.github.sdev.meinfreund.adapters.out.persistence

import java.time.LocalDateTime
import io.github.sdev.meinfreund.domain.entities.Expense

case class ExpenseEntity(
    name: String,
    description: Option[String],
    categoryId: Option[Int],
    date: LocalDateTime,
    amountUsd: BigDecimal,
    amountArs: BigDecimal,
    id: Option[Long] = None
)

object ExpenseEntity:

  extension (expense: Expense)
    def toEntity(): ExpenseEntity = ExpenseEntity(
      name = expense.name,
      description = expense.description,
      categoryId = expense.category.map(c => c.id),
      date = expense.date,
      amountUsd = expense.amountUsd,
      amountArs = expense.amountArs,
      id = expense.id
    )
