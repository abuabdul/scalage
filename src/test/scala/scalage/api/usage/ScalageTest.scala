package scalage.api.usage

import java.io.File
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import scalage.SwiftConnector
import scalage.SwiftObject
import java.util.Date
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalageSpecification extends Specification {

  val swiftCxn = SwiftConnector("admin:admin", "admin",
    "http://15.185.162.30:8080/auth/v1.0")

  val account = swiftCxn.connect

  "After login, Scalage's Account object " should {

    val contcont = account.listContainers.size
    val containerName = Fixture.containerName(contcont + 1)

    println("containe created with name " + containerName)

    "list all containters" in {
      assert(account.listContainers.isEmpty || !account.listContainers.isEmpty)
    }

    "create new Container" in {
      val newCont = account.createContainer(containerName)
      val cont = account.getContainer(containerName).get
      assert(contcont + 1 == account.listContainers.size)
    }

    "The Container should upload object" in {
      val cont = account.listContainers
      // the code works if and only if we call the above listContainers method 
      val curCont = account.getContainer(containerName).get
      curCont.uploadObject(Fixture.swiftObj)
    }

    "The Container should list objects" in {
      val cont = account.listContainers
      val currCont = account.getContainer(containerName).get
      currCont.listItems != null
    }
  }
}

object Fixture {
  val containerName = (suff: Int) => "scalage_" + suff
  val f2up = new File("ppt.pptx")
  val swiftObj = SwiftObject("ppt", "ppt", f2up)
}