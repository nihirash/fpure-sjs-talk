package com.nihirash.talk

import cats.effect.Effect
import org.http4s.HttpApp
import org.http4s.server.Router
import org.http4s.implicits._

object router {
  def asApp[F[_]: Effect](t: TodoService[F]): HttpApp[F] = Router("/api" -> t.service).orNotFound
}
