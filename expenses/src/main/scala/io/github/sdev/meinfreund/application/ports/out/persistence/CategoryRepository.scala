package io.github.sdev.meinfreund.application.ports.out.persistence

import io.github.sdev.meinfreund.domain.entities.Category

trait CategoryRepository[F[_]]:

  def getAll(): F[List[Category]]
