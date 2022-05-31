package io.github.sdev.meinfreund.adapters.out.quotation

import io.github.sdev.meinfreund.application.quotation.QuotationProvider
import cats.syntax.all._
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import cats.effect.Sync
import org.typelevel.log4cats.Logger

object QuotationProviderImpl:

  def make[F[_]: Sync: Logger]() =
    new QuotationProvider[F] {

      private lazy val URL     = "https://www.ambito.com"
      private lazy val browser = JsoupBrowser()
      override def getQuote(): F[BigDecimal] =
        for
          _     <- Logger[F].info(s"Scrapping news from $URL")
          doc   <- browser.get(URL).pure[F]
          quote <- 42.pure[F] // TODO scrap website
        yield quote
    }
