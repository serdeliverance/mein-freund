package io.github.sdev.meinfreund.application

import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.github.sdev.meinfreund.domain.entities.Credit
import io.github.sdev.meinfreund.application.quotation.QuotationProvider
import cats.syntax.all._
import cats.Monad
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.application.util.ExpenseUtil.toExpensePeriod
import io.github.sdev.meinfreund.domain.usecases.AddExpensesUseCase

object AddExpensesService:

  def make[F[_]: Monad](
      quotationProvider: QuotationProvider[F],
      expenseRepository: ExpenseRepository[F]
  ) =
    new AddExpensesUseCase[F] {
      override def addExpenses(originalExpenses: List[OriginalExpense]): F[Credit] =
        for
          quote    <- quotationProvider.getQuote()
          expenses <- originalExpenses.map(_.toExpense(quote)).pure[F]
          _        <- expenseRepository.saveBulk(expenses)
          credit <-
            expenses match
              case head :: _ =>
                Credit(
                  expenses.map(_.amountUsd).sum,
                  quote,
                  head.date.toExpensePeriod
                ).pure[F]
              case _ =>
                Credit(100, 100, "2020-2")
                  .pure[F] // TODO dummy data... add error handling (with ApplicativeError or MonadError)
        yield credit
    }
