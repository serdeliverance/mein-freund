package io.github.sdev.meinfreund.adapters.out.persistence

import skunk.Codec
import io.github.sdev.meinfreund.domain.entities.Expense

object SqlCodecs:
  val expenseCodec: Codec[Expense] = ???
