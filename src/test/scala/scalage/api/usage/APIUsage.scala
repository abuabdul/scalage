package scalage.api.usage

import scalage.ScalageConverters._
import scalage.ContainerModel
import scalage.SwiftAuthRequest
import scalage.SwiftConnector._
import java.io.File
import scalage.SwiftObject

object APIUsage extends App {

  // connect to swift 
  val url = "http://15.185.162.30:8080/auth/v1.0"
  // http://15.185.191.158:8080/auth/v1.0
  val account = connect(
    auth("admin:admin", "admin", url))
  // val cont2 = account.createContainer("scala")
  //  println(account.listContainers.foreach(obj => {
  //    println(obj.name)
  //  }))

  val cont3 = account.getContainer("scalage")
  println(cont3.isDefined)
  //  //
  //  val container = cont2.get
  //  //  container.listItems
  //  //
  //  val f2up = new File("ppt.pptx")
  //  val swiftObj = SwiftObject("ppt", "ppt", f2up)
  //  container.uploadObject(swiftObj)
  //  println(container.listItems)
}