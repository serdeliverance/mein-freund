package io.github.sdev.meinfreund.application.ports.out.messaging

trait KafkaProducer[F[_]]:

  def publish[T](body: T, topic: String): F[Unit]
