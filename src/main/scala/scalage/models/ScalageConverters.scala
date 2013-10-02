package scalage.models

import spray.json._
import DefaultJsonProtocol._
import scalage.AccountFactory

object ScalageConverters {
  import scalage.models.ScalageModelProtocol._

  private val containerJson = (y: String) => "{\"containers\" : " + y + " }"
  implicit val toContainers = (x: String) => {
    val container = containerJson(x).asJson
    val containers = container.convertTo[Containers]
    containers
  }
}
