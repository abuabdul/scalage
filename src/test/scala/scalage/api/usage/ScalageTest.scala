package scalage.api.usage

import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner._
import scalage.SwiftConnector

@RunWith(classOf[JUnitRunner])
class APITest extends Specification {
  val swiftCxn = SwiftConnector("admin:admin", "admin",
    "http://15.185.162.30:8080/auth/v1.0")
  val account = swiftCxn.connect

  "The API if connected with url and primary auth" should {
    val accountModel = account.getAccountModel
    "get Account whose AccountModel" in {
      assert(accountModel.authToken != null)
      assert(accountModel.storageToken != null)
      assert(accountModel.authToken != null)
    }
  }

  "Once logged in and using the Account object the API" should {
    var containers = 0
    "allow its client to list all containers" in {
      account.listContainers.foreach(f=>println(f.name))
      containers = account.listContainers.size
      assert(account.listContainers.isEmpty || !account.listContainers.isEmpty)
    }

    "allow its client to create a new empty Container" in {
      val newCont = account.createContainer("scalage05")
      println(newCont.listItems.isDefined)
    }
    
    "allow its client to upload objects to container" in {
      
    }
  }
}