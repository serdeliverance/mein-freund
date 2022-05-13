package io.github.sdev.meinfreund.application.util

import cats.effect.Resource
import skunk.Session

object PersistenceUtils:

  opaque type SessionPool[F[_]] = Resource[F, Session[F]]
