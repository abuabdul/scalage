package scalage.api.usage

import scalage.ScalageConverters._
import scalage.ContainerModel
import scalage.SwiftAuthRequest
import scalage.SwiftConnector._

object APIUsage extends App {

  // connect to swift 

  val account = connect(
    auth("admin:admin", "admin", "http://15.185.191.158:8080/auth/v1.0"))

  // account.createContainer("welcome")

  println(account.listContainers)

  println(account.getContainer("welcome"))

  println(account.getContainer("wel").isDefined)

}