package com.nihirash.talk

import slinky.core.ReactComponentClass

import scala.scalajs.js.annotation.JSExportTopLevel

object Exports {
  @JSExportTopLevel("Component")
  val component = SomeComponent : ReactComponentClass
}
