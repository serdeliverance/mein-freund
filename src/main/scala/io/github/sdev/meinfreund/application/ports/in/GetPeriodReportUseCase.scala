package io.github.sdev.meinfreund.application.ports.in

import io.github.sdev.meinfreund.domain.entities.Report

trait GetPeriodReportUseCase[F[_]]:

  def getPeriodReport(month: Int, year: Int): F[Report]
