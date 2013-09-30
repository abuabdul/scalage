package scalage

import spray.json._
import scalage.models.Account
import DefaultJsonProtocol._

class AccountFactory {

  var requestString = ""
  private val account = (key: String, value: String) => {
    this.requestString = this.requestString + s" ${key} : ${value} " + ","
    this
  }

  def userName(uname: String) = account("name", uname)

  def password(password: String) = account("password", password)

  def container(container: String) = account("container", container)

  def createAccount = {
    val json = "{" + this.requestString.substring(0, this.requestString.length() - 1) + "}"
    import scalage.models.ScalageModelProtocol._
    val x = json.toJson
    x.convertTo[Account]
  }

}

object AccountFactory extends App {
  val af = new AccountFactory
  println(af.userName("prassee").password("prassee").container("scalage").createAccount)
}

