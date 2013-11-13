package scalage.api.usage

import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner._
import scalage.SwiftConnector

@RunWith(classOf[JUnitRunner])
class MySpecTest extends Specification {
  var x = 0

  val swiftCxn = SwiftConnector("admin:admin", "admin",
    "http://15.185.191.158:8080/auth/v1.0")

  "The 'Hello world' string" should {
    "contain 11 characters" in {
      x = 1
      
      "Hello world" must have size (11)
    }
    "start with 'Hello'" in {
      x must equalTo(1)
      "Hello world" must startWith("Hello")
    }
    "end with 'world'" in {
      "Hello world" must endWith("world")
    }
  }

}

class APITest extends Specification {
  
}