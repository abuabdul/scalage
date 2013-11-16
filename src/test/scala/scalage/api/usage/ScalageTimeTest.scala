package scalage.api.usage

import java.io.File
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import scalage.SwiftConnector
import scalage.SwiftObject
import java.util.Date
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class APITimeTest extends Specification {
  println("started spec")

  "Once logged in and using the Account object the API" should {
    println("started shoud matches")

    val timeFix =(cnt:Int) => TimeFixture.containerName + cnt + 1
    val x = timeFix(0)
    "allow its client to list all containters" in {
      println(x)
      println(x)
    }
  }
  
  "if started again" should {
    println("started again") 
    
    
  }
}

object TimeFixture {
  val containerName = "scalage_" + new Date().getTime()
}