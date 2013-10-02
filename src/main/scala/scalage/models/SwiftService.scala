package scalage.models

import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.header.Headers
import java.net.URL
import uk.co.bigbeeconsultants.http.header.HeaderName
import uk.co.bigbeeconsultants.http.request.RequestBody
import uk.co.bigbeeconsultants.http.header.MediaType

object SwiftService {
  private val client = new HttpClient

  def getSwiftResponse = (x: SwiftAuthRequest) => {
    var resp = SwiftAuthResponse("", "", "")
    client.get(new URL(x.url),
      Headers(Map("x-auth-user" -> x.name,
        "x-auth-key" -> x.password))).headers.foreach(f => {
        f.name match {
          case "X-Storage-Url" =>
            resp = SwiftAuthResponse(f.value, resp.authToken, resp.storageToken)
          case "X-Auth-Token" =>
            resp = SwiftAuthResponse(resp.storageUrl, f.value, resp.storageToken)
          case "X-Storage-Token" =>
            resp = SwiftAuthResponse(resp.storageUrl, resp.authToken, f.value)
          case _ =>
        }
      })
    resp
  }

  def apply(swiftReq: SwiftAuthRequest,
    httpClient: HttpClient) = new SwiftService(getSwiftResponse(swiftReq), client)

}

class SwiftService(swiftResponse: SwiftAuthResponse, client: HttpClient) {

  def listContainers = client.get(new URL(swiftResponse.storageUrl + "?format=json"),
    Headers(Map("X-Auth-Token" -> swiftResponse.authToken))).body.asString

  def createContainer(cont: String) = {
    val rb = RequestBody.apply("", MediaType.TEXT_PLAIN)
    client.put(new URL(swiftResponse.storageUrl + "/" + cont), rb,
      Headers(Map("X-Auth-Token" -> swiftResponse.authToken)))
  }

  def listObjectsInContainer(cont: String) = {
    val rb = RequestBody.apply("", MediaType.TEXT_PLAIN)
    val containers = client.get(new URL(swiftResponse.storageUrl + "/" + cont),
      Headers(Map("X-Auth-Token" -> swiftResponse.authToken)))
    containers.body
  }
}

