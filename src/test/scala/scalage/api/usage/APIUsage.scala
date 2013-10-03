package scalage.api.usage

import scalage.models.ScalageConverters._
import scalage.models.Container
import scalage.models.SwiftAuthRequest
import scalage.models.SwiftConnector
import scalage.models.ContainerService

object APIUsage extends App {

  // connect to swift 
  val account = SwiftConnector.connect(
    SwiftAuthRequest("http://15.185.191.158:8080/auth/v1.0",
      "admin:admin", "admin"))

  // create container   
  // contSvc.createContainer("scalage-swift-02")

  // list all containers
  val contSvc = new ContainerService(account)

  println("listing containers")
  contSvc.listContainers.foreach(container => {
    println(container.name)
  })

  println("listing objects in container")
  contSvc.listObjectsInContainer("scalage")

}