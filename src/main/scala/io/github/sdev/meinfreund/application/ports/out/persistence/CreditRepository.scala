package io.github.sdev.meinfreund.application.ports.out.persistence

import io.github.sdev.meinfreund.domain.entities.Credit

trait CreditRepository[F[_]]:

  def save(credit: Credit): F[Credit]
