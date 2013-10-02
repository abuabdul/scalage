package scalage.models

import spray.json._
import DefaultJsonProtocol._
import scalage.AccountFactory

object ScalageConverters {
  import scalage.models.ScalageModelProtocol._

  private val containerJson = (y: String) => "{\"containers\" : " + y + " }"
  implicit val toContainers = (x: String) => {
    val container = containerJson(x).asJson
    container.convertTo[Containers]
  }
}

object Main extends App {

  import scalage.models.ScalageConverters._

  val x = "[{\"count\": 0, \"bytes\": 0, \"name\": \"scalage\"}, {\"count\": 0, \"bytes\": 0, \"name\": \"scalage-swift\"}]".containers
  x.foreach(f => {
    println(f.name)
  })
}