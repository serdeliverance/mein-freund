package io.github.sdev.meinfreund.adapters.in.rest.json

import io.circe.Decoder
import io.circe.Encoder
import io.github.sdev.meinfreund.domain.entities.Credit
import io.github.sdev.meinfreund.domain.entities.OriginalExpense
import io.circe.generic.semiauto.deriveDecoder
import io.github.sdev.meinfreund.domain.entities.Category

object SerDes:

  object CreditFields:
    val AMOUNT_USD      = "amount_usd"
    val CONVERSION_RATE = "conversion_rate"
    val PERIOD          = "period"

  /*
    Semiauto-manual definition in order to have snake case fields (because of circeGenericExtras issues crossBuilding scala 3)
   */
  given creditEncoder: Encoder[Credit] =
    Encoder.forProduct3(CreditFields.AMOUNT_USD, CreditFields.CONVERSION_RATE, CreditFields.PERIOD)(c =>
      (c.amountUsd, c.conversionRate, c.period)
    )

  given categoryDecoder: Decoder[Category] = deriveDecoder

  given expenseDecoder: Decoder[OriginalExpense] = deriveDecoder
