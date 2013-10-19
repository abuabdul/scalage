package scalage

import spray.json._
import spray.json.DefaultJsonProtocol._
import scalage.ScalageModelProtocol.contsModelFormat

object ScalageConverters {
  import scalage.ScalageModelProtocol._
  private val containerJson = (y: String) => "{\"containers\" : " + y + " }"
  implicit val toContainers = (x: String) => {
    val container = containerJson(x).asJson
    val containers = container.convertTo[Containers]
    containers
  }
}
