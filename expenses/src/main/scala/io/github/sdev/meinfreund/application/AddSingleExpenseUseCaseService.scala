package io.github.sdev.meinfreund.application

import cats.Monad
import cats.syntax.all._
import io.github.sdev.meinfreund.application.ports.out.messaging.KafkaProducer
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.application.ports.out.persistence.CreditRepository
import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.github.sdev.meinfreund.domain.entities.Expense
import io.github.sdev.meinfreund.domain.entities.CurrencyConverter.convert
import io.github.sdev.meinfreund.domain.usecases.AddSingleExpenseUseCase
import io.github.sdev.meinfreund.application.quotation.QuotationProvider
import io.github.sdev.meinfreund.domain.entities.Credit
import io.github.sdev.meinfreund.domain.entities.ExpenseId
import io.github.sdev.meinfreund.application.util.ExpenseUtil._
import org.typelevel.log4cats.Logger
import cats.Applicative

class AddExpenseUseCaseService[F[_]: Monad: Logger: Applicative](
    quotationProvider: QuotationProvider[F],
    expenseRepository: ExpenseRepository[F],
    creditRepository: CreditRepository[F],
    kafkaProducer: KafkaProducer[F]
) extends AddSingleExpenseUseCase[F]:

  override def addExpense(expense: OriginalExpense): F[Credit] =
    for
      quote      <- quotationProvider.getQuote()
      newExpense <- convert(expense, quote).pure[F]
      expense    <- expenseRepository.save(newExpense)
      _          <- kafkaProducer.publish[Expense](expense, "expense-added")
      newCredit <- Credit(
        expense.amountUsd,
        expense.id.toExpenseIdList,
        expense.date.toExpensePeriod
      )
        .pure[F]
      credit <- creditRepository.save(newCredit)
    yield credit
