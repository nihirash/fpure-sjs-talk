package com.nihirash.talk

import com.nihirash.talk.models.TodoItem
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{className, div}

@react class SomeComponent extends StatelessComponent{
  case class Props(element: TodoItem)

  def render(): ReactElement = {

    div(className := styles.someClass)(props.element.toString)
  }
}
