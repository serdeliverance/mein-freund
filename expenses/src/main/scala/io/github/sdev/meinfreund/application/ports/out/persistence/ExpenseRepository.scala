package io.github.sdev.meinfreund.application.ports.out.persistence

import io.github.sdev.meinfreund.domain.entities.Expense

trait ExpenseRepository[F[_]]:

  def save(expense: Expense): F[Expense]
