package io.github.sdev.meinfreund.domain.usecases

import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.github.sdev.meinfreund.domain.entities.Credit

trait AddExpensesUseCase[F[_]]:

  def addExpenses(expenses: List[OriginalExpense]): F[Credit]
