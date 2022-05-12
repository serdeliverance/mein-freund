package io.github.sdev.meinfreund.domain.usecases

import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.github.sdev.meinfreund.domain.entities.Expense

trait AddExpenseUseCase[F[_]]:

  def addExpense(expense: OriginalExpense): F[Expense]
