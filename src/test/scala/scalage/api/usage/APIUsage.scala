package scalage.api.usage

import scalage.ScalageConverters._
import scalage.ContainerModel
import scalage.SwiftAuthRequest
import java.io.File
import scalage.SwiftObject
import scalage.SwiftConnector

object APIUsage extends App {

  val swiftCxn = SwiftConnector("admin:admin", "admin",
    "http://15.185.191.158:8080/auth/v1.0")

  // connect to swift 
  val url = "http://15.185.162.30:8080/auth/v1.0"
  // http://15.185.191.158:8080/auth/v1.0

  val cont3 = account.getContainer("scalage")
  println(cont3.isDefined)

  val account = swiftCxn.connect

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

