package io.github.sdev.meinfreund.application

import munit.CatsEffectSuite
import org.mockito.MockitoSugar.when
import org.mockito.MockitoSugar.mock
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import cats.effect.IO
import io.github.sdev.meinfreund.application.quotation.QuotationProvider

class AddSingleExpenseUseCaseServiceSpec extends CatsEffectSuite {

  private val expenseRepository = mock[ExpenseRepository[IO]]
  private val quotationProvider = mock[QuotationProvider[IO]]

  private val subject = new AddExpenseUseCaseService[IO](quotationProvider, expenseRepository)

  // TODO complete test
  test("add expense") {}
}
