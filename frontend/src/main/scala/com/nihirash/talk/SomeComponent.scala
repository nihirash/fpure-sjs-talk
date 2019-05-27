package com.nihirash.talk

import com.nihirash.talk.models.TodoItem
import org.scalajs.dom.Event
import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.{className, div, input, onClick}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("./resources/component.css", JSImport.Default)
@js.native
object styles extends js.Object {
  val todoRow: String = js.native
  val todoItemText: String = js.native
}

@react class SomeComponent extends StatelessComponent{
  case class Props(element: TodoItem, upd: (TodoItem) => Unit)

  def render(): ReactElement =
    div(className := styles.todoRow,
      onClick := ((e: Event) => props.upd(props.element.copy(isDone = true))))(
      div(if (props.element.isDone) "Done" else "Not done"),
      div(className := styles.todoItemText)(props.element.text)
  )
}
