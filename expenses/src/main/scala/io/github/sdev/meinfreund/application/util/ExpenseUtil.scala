package io.github.sdev.meinfreund.application.util

import java.time.LocalDateTime
import io.github.sdev.meinfreund.domain.entities.ExpenseId

object ExpenseUtil:

  extension (date: LocalDateTime) def toExpensePeriod: String = s"${date.getYear}-${date.getMonth}"

  // TODO think if this method makes sense here or should be an smart constructor of ExpenseId case class
  extension (maybeId: Option[Long])
    def toExpenseIdList: List[ExpenseId] =
      maybeId match
        case Some(expId) => List(ExpenseId(expId))
        case None        => List.empty[ExpenseId]
