package com.nihirash.talk

import com.nihirash.talk.models.TodoItem
import io.circe.{Decoder, Encoder}

trait codecs {
  import io.circe.generic.semiauto._

  implicit val todoItemDecoder: Decoder[TodoItem] = deriveDecoder
  implicit val todoItemEncoder: Encoder[TodoItem] = deriveEncoder
}
