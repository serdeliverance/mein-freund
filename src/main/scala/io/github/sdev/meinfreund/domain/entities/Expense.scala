package io.github.sdev.meinfreund.domain.entities

import java.time.LocalDateTime

// TODO add receipt path url
case class Expense(
    name: String,
    description: Option[String],
    category: Option[Category],
    date: LocalDateTime,
    amountUsd: BigDecimal,
    amountArs: BigDecimal,
    id: Option[Long] = None
)
