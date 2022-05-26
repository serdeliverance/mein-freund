package io.github.sdev.meinfreund.application.ports.in

import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.github.sdev.meinfreund.domain.entities.Expense
import io.github.sdev.meinfreund.domain.entities.Credit

trait AddSingleExpenseUseCase[F[_]]:

  def addExpense(expense: OriginalExpense): F[Credit]
