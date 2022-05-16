package io.github.sdev.meinfreund.application

import io.github.sdev.meinfreund.domain.usecases.AddExpensesUseCase
import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.github.sdev.meinfreund.domain.entities.Credit
import io.github.sdev.meinfreund.application.quotation.QuotationProvider
import cats.syntax.all._
import cats.Monad
import io.github.sdev.meinfreund.domain.entities.CurrencyConverter.convert
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.application.util.ExpenseUtil.toExpensePeriod

class AddExpenseUseCaseService[F[_]: Monad](
    quotationProvider: QuotationProvider[F],
    expenseRepository: ExpenseRepository[F]
) extends AddExpensesUseCase[F]:
  override def addExpenses(originalExpenses: List[OriginalExpense]): F[Credit] =
    for
      quote    <- quotationProvider.getQuote()
      expenses <- originalExpenses.map(exp => convert(exp, quote)).pure[F]
      _        <- expenseRepository.saveBulk(expenses)
      credit <-
        Credit(
          expenses.map(_.amountUsd).sum,
          quote,
          expenses.head.date.toExpensePeriod // TODO fix if empty list it will explode
        )
          .pure[F]
    yield credit
