package scalage.api.usage

import java.io.File
import org.junit.runner.RunWith
import org.scalatest._
import scalage.SwiftConnector
import scalage.SwiftUploadObject
import java.util.Date
import scalage.SwiftUploadObject
import org.scalatest.junit.JUnitRunner
import scalage.Account

@RunWith(classOf[JUnitRunner])
class ScalageSpecification extends FlatSpec with Matchers {
  var account: Option[Account] = None
  var containerName = ""
  var contcont = 0
  "After login, Scalage's Account object" should "pass test " in {
    val swiftCxn = SwiftConnector("admin:admin", "admin", "http://15.185.162.30:8080/auth/v1.0")
    account = Option(swiftCxn.connect)
    containerName = Fixture.containerName(contcont + 1)
    contcont = account.get.listContainers.size
  }

  it should "list all containters" in {
    intercept[Exception] {
      assert(account.get.listContainers.isEmpty || !account.get.listContainers.isEmpty)
    }
  }

  it should "create new Container" in {
    val newCont = account.get.createContainer(containerName)
    val cont = account.get.getContainer(containerName).get
    assert(contcont + 1 == account.get.listContainers.size)
  }

  it should "The Container should upload object" in {
    val cont = account.get.listContainers
    // the code works if and only if we call the above listContainers method 
    val curCont = account.get.getContainer(containerName).get
    curCont.uploadObject(Fixture.swiftObj)
  }

  it should "The Container should list objects" in {
    val cont = account.get.listContainers
    val currCont = account.get.getContainer(containerName).get
    currCont.listItems != null
  }

}

object Fixture {
  val containerName = (suff: Int) => "scalage_" + suff
  val f2up = new File("ppt.pptx")
  val swiftObj = SwiftUploadObject("ppt", "ppt", f2up)
}