package io.github.sdev.meinfreund.adapters.in.rest.json

import io.circe.Decoder
import io.circe.Encoder
import io.github.sdev.meinfreund.domain.entities.Credit
import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.circe.generic.semiauto.deriveEncoder

object SerDes:

  object CreditFields:
    val AMOUNT_USD      = "amount_usd"
    val CONVERSION_RATE = "conversion_rate"
    val PERIOD          = "period"

  given creditEncoder: Encoder[Credit] =
    Encoder.forProduct3(CreditFields.AMOUNT_USD, CreditFields.CONVERSION_RATE, CreditFields.PERIOD)(c =>
      (c.amountUsd, c.conversionRate, c.period)
    )

  // TODO
  given expenseDecoder: Decoder[OriginalExpense] = ???
