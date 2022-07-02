package io.github.sdev.meinfreund.adapters.out.quotation

import io.github.sdev.meinfreund.application.quotation.QuotationProvider
import cats.syntax.all._
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import cats.effect.Sync
import org.typelevel.log4cats.Logger
import cats.effect.IOApp
import cats.effect.IO
import cats.effect.ExitCode
import net.ruippeixotog.scalascraper.model.Element
import org.typelevel.log4cats.slf4j.Slf4jLogger

object QuotationProviderImpl:

  def make[F[_]: Sync: Logger]() =
    new QuotationProvider[F] {

      private lazy val URL     = "https://www.ambito.com"
      private lazy val browser = JsoupBrowser()

      override def getQuote(): F[BigDecimal] =
        for
          _   <- Logger[F].info(s"Scrapping news from $URL")
          doc <- browser.get(URL).pure[F]
          quote <- Sync[F]
            .delay(
              doc >> element(
                "#swiper-economic-indicators"
              )
            )
            .map(element => parse(element))
            .map {
              case Right(quote) => quote
              case Left(parsingError) =>
                ??? // TODO look for an idiomatic way for error handling (either or using IO monad/cats mechanism)
            }
        yield quote

      // TODO implement
      private def parse(element: Element): Either[ParsingError, BigDecimal] = {
        println(element)
        Right(BigDecimal(2))
      }
    }

// remove after testings
object Sample extends IOApp:

  import QuotationProviderImpl._

  given logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  def run(args: List[String]) =
    make[IO]().getQuote().map(println).as(ExitCode.Success)
