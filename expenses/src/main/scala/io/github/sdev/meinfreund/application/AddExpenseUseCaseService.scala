package io.github.sdev.meinfreund.application

import cats.Monad
import cats.syntax.all._
import io.github.sdev.meinfreund.application.ports.out.messaging.KafkaProducer
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.github.sdev.meinfreund.domain.entities.Expense
import io.github.sdev.meinfreund.domain.entities.CurrencyConverter.convert
import io.github.sdev.meinfreund.domain.usecases.AddExpenseUseCase
import io.github.sdev.meinfreund.application.quotation.QuotationProvider

class AddExpenseUseCaseService[F[_]: Monad](
    quotationProvider: QuotationProvider[F],
    expenseRepository: ExpenseRepository[F],
    kafkaProducer: KafkaProducer[F]
) extends AddExpenseUseCase[F]:

  override def addExpense(expense: OriginalExpense): F[Expense] =
    for
      quote          <- quotationProvider.getQuote()
      expense        <- convert(expense, quote).pure[F]
      expenseCreated <- expenseRepository.save(expense)
      _              <- kafkaProducer.publish[Expense](expenseCreated, "expense-added")
    yield expenseCreated
