package io.github.sdev.meinfreund.stubs

import java.util.Locale.Category
import java.time.LocalDateTime

trait ExpenseStubs {

  val expense = Expense("psico", None, Some(Category(1, "psico"), LocalDateTime.now.minusWeeks(2)), 2500)

}
