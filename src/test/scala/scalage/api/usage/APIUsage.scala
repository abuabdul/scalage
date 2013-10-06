package scalage.api.usage

import scalage.ScalageConverters._
import scalage.ContainerModel
import scalage.SwiftAuthRequest
import scalage.SwiftConnector._
import java.io.File
import scalage.SwiftObject

object APIUsage extends App {

  // connect to swift 
  val account = connect(
    auth("admin:admin", "admin", "http://15.185.191.158:8080/auth/v1.0"))

  // val cont2 =  account.createContainer("welcome3")

  println(account.listContainers)

  val cont2 = account.getContainer("scalage")
  println(cont2.isDefined)

  val container = cont2.get

  container.listItems

  val f2up = new File("ppt.pptx")
  val swiftObj = SwiftObject("ppt", "ppt", f2up)
  // container.uploadObject(swiftObj)
  println(container.listItems)
}