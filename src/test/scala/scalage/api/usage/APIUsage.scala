package scalage.api.usage

import scalage.models.ScalageConverters._
import scalage.models.SwiftService
import scalage.models.SwiftAuthRequest

object APIUsage extends App {

  // connect to swift 
  val service = SwiftService(
    SwiftAuthRequest("http://15.185.191.158:8080/auth/v1.0",
      "admin:admin", "admin"))

  // create container   
  // service.createContainer("scalage-swift-02")

  // list all containers
  service.listContainers.containers.foreach(container => {
    println(container.name)
  })
  
  service.listObjectsInContainer("scalage")

}