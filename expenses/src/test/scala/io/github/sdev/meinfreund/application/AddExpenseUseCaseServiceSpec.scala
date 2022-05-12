package io.github.sdev.meinfreund.application

import munit.CatsEffectSuite
import org.mockito.MockitoSugar.when
import org.mockito.MockitoSugar.mock
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import cats.effect.IO
import io.github.sdev.meinfreund.application.ports.out.messaging.KafkaProducer

class AddExpenseUseCaseServiceSpec extends CatsEffectSuite {

  private val expensesRepository = mock[ExpenseRepository[IO]]
  private val kafkaProducer      = mock[KafkaProducer[IO]]

  private val subject = new AddExpenseUseCaseService[IO](expenseRepository, kafkaProducer)

  // TODO complete test
  test("add expense") {
    val expense = Expense()

    when(expensesRepository.save()).thenReturn()
  }
}
