package io.github.sdev.meinfreund.application.quotation

trait QuotationProvider[F[_]]:

  def getQuote(): F[BigDecimal]
