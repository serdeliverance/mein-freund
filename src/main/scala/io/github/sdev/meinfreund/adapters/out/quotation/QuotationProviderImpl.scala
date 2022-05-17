package io.github.sdev.meinfreund.adapters.out.quotation

import io.github.sdev.meinfreund.application.quotation.QuotationProvider

class QuotationProviderImpl[F[_]] extends QuotationProvider[F]:

  // TODO
  override def getQuote(): F[BigDecimal] = ???
