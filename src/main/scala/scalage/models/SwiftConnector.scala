package scalage.models

import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.header.Headers
import java.net.URL
import uk.co.bigbeeconsultants.http.header.HeaderName
import uk.co.bigbeeconsultants.http.request.RequestBody
import uk.co.bigbeeconsultants.http.header.MediaType

object SwiftConnector {
  private val client = new HttpClient

  def connect = (x: SwiftAuthRequest) => {
    var resp = Account("", "", "")
    client.get(new URL(x.url), Headers(Map("x-auth-user" -> x.name,
      "x-auth-key" -> x.password))).headers.foreach(f => {
      f.name match {
        case "X-Storage-Url" =>
          resp = Account(f.value, resp.authToken, resp.storageToken)
        case "X-Auth-Token" =>
          resp = Account(resp.storageUrl, f.value, resp.storageToken)
        case "X-Storage-Token" =>
          resp = Account(resp.storageUrl, resp.authToken, f.value)
        case _ =>
      }
    })
    resp
  }
}

class ContainerService(swiftResponse: Account) {
  private val client = new HttpClient
  private val rb = RequestBody.apply("", MediaType.TEXT_PLAIN)
  private val stdHdrs = Headers(Map("X-Auth-Token" -> swiftResponse.authToken))

  def listContainers = {
    import scalage.models.ScalageConverters._
    client.get(new URL(swiftResponse.storageUrl + "?format=json"), stdHdrs).body.asString.containers
  }

  def createContainer(cont: String) =
    client.put(new URL(swiftResponse.storageUrl + "/" + cont), rb, stdHdrs)

  def listObjectsInContainer(cont: String) =
    client.get(new URL(swiftResponse.storageUrl + "/" + cont + "?format=json"), stdHdrs).body

  def getContainer(name: String) = listContainers.filter(x => x.name.equals(name)).head
}


