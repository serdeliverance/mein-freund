package io.github.sdev.meinfreund.domain.entities

import java.time.LocalDateTime

// TODO add receipt path url
case class OriginalExpense(
    name: String,
    description: Option[String],
    category: Option[Category],
    amount: BigDecimal
)
