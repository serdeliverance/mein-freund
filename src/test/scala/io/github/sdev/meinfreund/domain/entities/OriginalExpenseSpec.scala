package io.github.sdev.meinfreund.domain.entities

import munit.CatsEffectSuite
import cats.effect.IO

class OriginalExpenseSpec extends CatsEffectSuite {
  test("original expense conversion") {
    val originalExpense = OriginalExpense("light service", None, Some(Category(1, "services")), 1000)

    val result = originalExpense.toExpense(1.1)

    IO(result).map(exp => assertEquals(BigDecimal(1100), exp.amountUsd))
  }
}
