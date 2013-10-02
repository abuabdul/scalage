package scalage.models

import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.header.Headers
import java.net.URL
import uk.co.bigbeeconsultants.http.header.HeaderName
import uk.co.bigbeeconsultants.http.request.RequestBody
import uk.co.bigbeeconsultants.http.header.MediaType

object SwiftService extends App {

  case class SwiftResponse(storageUrl: String, authToken: String, storageToken: String)

  var resp = SwiftResponse("", "", "")
  val client = new HttpClient
  val swiftURL = "http://15.185.191.158:8080/auth/v1.0"

  val respHeaders = (x: String) => client.get(
    new URL(x), Headers(Map("x-auth-user" -> "admin:admin",
      "x-auth-key" -> "admin"))).headers

  respHeaders(swiftURL).foreach(f => {
    f.name match {
      case "X-Storage-Url" => resp = SwiftResponse(f.value, resp.authToken, resp.storageToken)
      case "X-Auth-Token" => resp = SwiftResponse(resp.storageUrl, f.value, resp.storageToken)
      case "X-Storage-Token" => resp = SwiftResponse(resp.storageUrl, resp.authToken, f.value)
      case _ =>
    }
  })

  println(resp)

  // createContainer("scalage")
  listContainers
  
  def listContainers = {
    val contList = client.get(new URL(resp.storageUrl + "?format=json"),
      Headers(Map("X-Auth-Token" -> resp.authToken)))
    println(contList.body)
  }

  def createContainer(cont: String) = {
    val rb = RequestBody.apply("", MediaType.TEXT_PLAIN)
    client.put(new URL(resp.storageUrl+"/"+cont),rb,Headers(Map("X-Auth-Token" -> resp.authToken)))
  }

}

