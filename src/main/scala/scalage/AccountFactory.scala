package scalage

import spray.json._
import DefaultJsonProtocol._
import scalage.models.Account

class AccountFactory {

  var requestString = ""
  private val account = (key: String, value: String) => {
    this.requestString = this.requestString + "\"" + s"${key}" + "\"" +" : " +"\"" + s"${value}"+"\"" + ","
    this
  }

  def userName(uname: String) = account("name", uname)

  def password(password: String) = account("password", password)

  def container(container: String) = account("container", container)

  def createAccount = {
    val json = "{" + this.requestString.substring(0, this.requestString.length() - 1) + "}"
    val x = json.asJson
    println(x)
    import scalage.models.ScalageModelProtocol._
    x.convertTo[Account]
  }

}

