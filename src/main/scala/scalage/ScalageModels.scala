package scalage

import spray.json._
import spray.json.DefaultJsonProtocol._

case class Containers(containers: List[ContainerModel])
case class ContainerModel(count: Int, bytes: Int, name: String)

case class AccountModel(storageUrl: String, authToken: String, storageToken: String)
case class SwiftAuthRequest(name: String, password: String,url: String)

object ScalageModelProtocol extends DefaultJsonProtocol {
  implicit val acModelFormat = jsonFormat3(AccountModel)
  implicit val contModelFormat = jsonFormat3(ContainerModel)
  implicit val contsModelFormat = jsonFormat1(Containers)
}