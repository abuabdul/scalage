package scalage

import java.net.URL

import scalage.ScalageConverters.toContainers.apply
import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.header.Headers
import uk.co.bigbeeconsultants.http.header.MediaType
import uk.co.bigbeeconsultants.http.request.RequestBody

object SwiftConnector {
  def apply(name: String, key: String, url: String) = {
    new SwiftConnector(SwiftAuthRequest(name, key, url))
  }
}

class SwiftConnector(x: SwiftAuthRequest) {

  private val client = new HttpClient

  def connect = {
    var resp = AccountModel("", "", "")
    client.get(new URL(x.url), Headers(Map("x-auth-user" -> x.name,
      "x-auth-key" -> x.password))).headers.foreach(f => {
      f.name match {
        case "X-Storage-Url" =>
          resp = AccountModel(f.value, resp.authToken, resp.storageToken)
        case "X-Auth-Token" =>
          resp = AccountModel(resp.storageUrl, f.value, resp.storageToken)
        case "X-Storage-Token" =>
          resp = AccountModel(resp.storageUrl, resp.authToken, f.value)
        case _ =>
      }
    })
    new Account(resp)
  }
}


