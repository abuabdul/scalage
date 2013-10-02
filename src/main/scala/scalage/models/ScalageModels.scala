package scalage.models

import spray.json._
import DefaultJsonProtocol._

case class Containers(containers: List[Container])
case class Container(count: Int, bytes: Int, name: String)
case class Account(name: String, password: String, container: String)

object ScalageModelProtocol extends DefaultJsonProtocol {
  implicit val acModelFormat = jsonFormat3(Account)
  implicit val contModelFormat = jsonFormat3(Container)
  implicit val contsModelFormat = jsonFormat1(Containers)
}