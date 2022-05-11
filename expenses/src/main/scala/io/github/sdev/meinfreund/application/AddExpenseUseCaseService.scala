package io.github.sdev.meinfreund.application

import io.github.sdev.meinfreund.domain.usecases.AddExpenseUseCase
import io.github.sdev.meinfreund.domain.entities.Expense
import io.github.sdev.meinfreund.application.ports.out.persistence.ExpenseRepository
import io.github.sdev.meinfreund.application.ports.out.messaging.KafkaProducer

class AddExpenseUseCaseService[F[_]](expenseRepository: ExpenseRepository[F], kafkaProducer: KafkaProducer[F])
    extends AddExpenseUseCase[F]:

  //   TODO validate => persist => publish
  override def addExpense(expense: Expense): F[Expense] = ???
