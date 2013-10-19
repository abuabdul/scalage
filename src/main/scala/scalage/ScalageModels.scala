package scalage

import spray.json._
import spray.json.DefaultJsonProtocol._
import java.io.File

case class Containers(containers: List[ContainerModel])
case class ContainerModel(count: Int, bytes: Int, name: String)
case class AccountModel(storageUrl: String, authToken: String, storageToken: String)
case class SwiftAuthRequest(name: String, password: String, url: String)
case class SwiftObject(name: String, path: String, file: File)

object ScalageModelProtocol extends DefaultJsonProtocol {
  implicit val acModelFormat = jsonFormat3(AccountModel)
  implicit val contModelFormat = jsonFormat3(ContainerModel)
  implicit val contsModelFormat = jsonFormat1(Containers)
}