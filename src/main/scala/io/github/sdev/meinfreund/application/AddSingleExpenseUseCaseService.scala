package io.github.sdev.meinfreund.application

import cats.Monad
import cats.syntax.all._
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.github.sdev.meinfreund.domain.entities.Expense
import io.github.sdev.meinfreund.domain.usecases.AddSingleExpenseUseCase
import io.github.sdev.meinfreund.application.quotation.QuotationProvider
import io.github.sdev.meinfreund.domain.entities.Credit
import io.github.sdev.meinfreund.domain.entities.ExpenseId
import io.github.sdev.meinfreund.application.util.ExpenseUtil._
import org.typelevel.log4cats.Logger
import cats.Applicative

class AddSingleExpenseUseCaseService[F[_]: Monad: Logger: Applicative](
    quotationProvider: QuotationProvider[F],
    expenseRepository: ExpenseRepository[F]
) extends AddSingleExpenseUseCase[F]:

  override def addExpense(originalExpense: OriginalExpense): F[Credit] =
    for
      quote   <- quotationProvider.getQuote()
      expense <- originalExpense.toExpense(quote).pure[F]
      _       <- expenseRepository.save(expense)
      credit <- Credit(
        expense.amountUsd,
        quote,
        expense.date.toExpensePeriod
      )
        .pure[F]
    yield credit
