package io.github.sdev.meinfreund.domain.usecases

import io.github.sdev.meinfreund.domain.entities.Report

trait GetPeriodReportUseCase[F[_]]:

  def getPeriodReport(month: Int, year: Int): F[Report]
