
package scalage.models

import spray.json._
import DefaultJsonProtocol._

case class Account(name: String, password: String, container: String)

object ScalageModelProtocol extends DefaultJsonProtocol {
  implicit val modelFormat = jsonFormat3(Account)
}