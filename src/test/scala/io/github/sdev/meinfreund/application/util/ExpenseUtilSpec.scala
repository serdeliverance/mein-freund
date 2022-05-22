package io.github.sdev.meinfreund.application.util

import munit.CatsEffectSuite
import java.time.LocalDateTime

import io.github.sdev.meinfreund.application.util.ExpenseUtil.toExpensePeriod
import cats.effect.IO

class ExpenseUtilSpec extends CatsEffectSuite {
  test("LocalDateTime to period conversion") {
    val aDate = LocalDateTime.of(2020, 2, 25, 1, 1)

    val result = aDate.toExpensePeriod

    IO(result).map(period => assertEquals("2020-2", period))
  }
}
