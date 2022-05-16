package io.github.sdev.meinfreund.adapters.in.rest.json

import io.circe.Decoder
import io.circe.Encoder
import io.circe.Printer
import io.github.sdev.meinfreund.domain.entities.Credit
import io.github.sdev.meinfreund.domain.entities.Expense

object SerDes:
  given customPrinter: Printer = Printer.noSpaces.copy(dropNullValues = true)

  // TODO
  given creditEncoder: Encoder[Credit] = ???

  // TODO
  given expenseDecoder: Decoder[Expense] = ???
