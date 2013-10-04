package scalage.api.usage

import scalage.ScalageConverters._
import scalage.ContainerModel
import scalage.SwiftAuthRequest
import scalage.SwiftConnector._
import scalage.ContainerService

object APIUsage extends App {

  // connect to swift 

  val account = connect(
    auth("admin:admin", "admin", "http://15.185.191.158:8080/auth/v1.0"))

  // account.createContainer("welcome")

  println(account.listContainers)

  println(account.getContainer("welcome"))

  println(account.getContainer("wel").isDefined)

  // create container   
  // contSvc.createContainer("scalage-swift-02")

  // list all containers
  /*val contSvc = new ContainerService(account, "scalage")

  println("listing containers")
  contSvc.listContainers.foreach(container => {
    println(container.name)
  })

  contSvc.getContainer

  println("listing objects in container")
  contSvc.listObjectsInContainer("scalage")*/

}