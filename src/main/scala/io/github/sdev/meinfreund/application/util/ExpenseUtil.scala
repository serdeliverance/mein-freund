package io.github.sdev.meinfreund.application.util

import java.time.LocalDateTime
import io.github.sdev.meinfreund.domain.entities.ExpenseId

object ExpenseUtil:

  extension (date: LocalDateTime) def toExpensePeriod: String = s"${date.getYear}-${date.getMonth}"
