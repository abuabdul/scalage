package scalage.models

import spray.json._
import DefaultJsonProtocol._
import scalage.AccountFactory

case class Account(name: String, password: String, container: String)

object ScalageModelProtocol extends DefaultJsonProtocol {
  implicit val modelFormat = jsonFormat3(Account)
}

object Main extends App {
   import scalage.models.ScalageModelProtocol._
   val ac = Account("pras","pras","cont")
   println(ac.toJson)
   val x = ac.toJson
   val aco = x.convertTo[Account]
   println(aco.name)
   
     val af = new AccountFactory
  println(af.userName("prassee").password("prassee").container("scalage").createAccount)
}