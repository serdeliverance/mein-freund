package io.github.sdev.meinfreund.stubs

import java.time.LocalDateTime
import io.github.sdev.meinfreund.domain.entities.Expense
import io.github.sdev.meinfreund.domain.entities.Category

trait ExpenseStubs {

  val expense = Expense("psico", None, Some(Category(1, "psico")), LocalDateTime.now.minusWeeks(2), 2500, 100)

}
