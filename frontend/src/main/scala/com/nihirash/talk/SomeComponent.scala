package com.nihirash.talk

import com.nihirash.talk.models.TodoItem
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

@react class SomeComponent extends StatelessComponent{
  case class Props(element: TodoItem)

  def render(): ReactElement = "hello"
}
