package com.nihirash.talk

import cats.effect.Effect
import org.http4s.dsl.Http4sDsl
import cats.effect.implicits._
import cats.implicits._
import org.http4s.HttpRoutes
import cats.effect.concurrent.Ref
import com.nihirash.talk.models.TodoItem

class TodoService[F[_]: Effect](store: Ref[F, List[TodoItem]]) extends Http4sDsl[F] with codecs {
  import org.http4s.circe.CirceEntityEncoder._
  import org.http4s.circe.CirceEntityDecoder._


  def service = HttpRoutes.of[F] {
    case GET -> Root => Ok(store.get)

    case req @ POST -> Root =>
      for {
        item <- req.as[TodoItem]
        _ <- store.modify(l =>
          l.find(_.id == item.id) match {
            case None => (l :+ item, l)
            case Some(_) => (l.map(i => if (i.id == item.id) item else i), l)
          }
        )
        r <- store.get
        response <- Created(r)
      } yield response
  }
}
