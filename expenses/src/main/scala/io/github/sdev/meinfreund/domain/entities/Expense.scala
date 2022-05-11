package io.github.sdev.meinfreund.domain.entities

import java.time.LocalDateTime

case class Expense(
    name: String,
    description: Option[String],
    category: Option[Category],
    date: LocalDateTime,
    amount: BigDecimal
)
