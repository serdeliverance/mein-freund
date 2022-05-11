package io.github.sdev.meinfreund.application

import io.github.sdev.meinfreund.domain.usecases.AddExpenseUseCase
import io.github.sdev.meinfreund.domain.entities.Expense
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository

class AddExpenseUseCaseService[F[_]](expenseRepository: ExpenseRepository[F]) extends AddExpenseUseCase[F]:
  override def addExpense(expense: Expense): F[Expense] = ???
