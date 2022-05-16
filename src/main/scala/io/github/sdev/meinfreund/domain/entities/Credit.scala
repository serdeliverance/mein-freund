package io.github.sdev.meinfreund.domain.entities

import java.time.LocalDateTime

// period: month-year (maybe refactor to refined types in the future)
case class Credit(amountUsd: BigDecimal, conversionRate: BigDecimal, period: String)
