package com.nihirash.talk

import cats.effect._
import cats.effect.concurrent.Ref
import org.http4s.server.blaze.BlazeServerBuilder
import cats.implicits._
import cats.effect.implicits._
import com.nihirash.talk.models.TodoItem

object Main extends IOApp {
  import scala.concurrent.ExecutionContext.Implicits.global
  implicit val cs: ContextShift[IO] = IO.contextShift(global)

  def run(args: List[String]): IO[ExitCode] = {

    val s = Ref.of[IO, List[TodoItem]](List(TodoItem("first", false, "Начать проект")))

    for {
      store <- s
      exitCode <- BlazeServerBuilder[IO]
        .bindHttp(8080, "127.0.0.1")
        .withHttpApp(router.asApp(new TodoService[IO](store)))
        .serve
        .compile
          .drain
          .as(ExitCode.Success)
    } yield exitCode
  }
}
