package scalage

import uk.co.bigbeeconsultants.http.request.RequestBody
import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.header.Headers
import uk.co.bigbeeconsultants.http.header.MediaType

trait AbstractCommand {
  val client = new HttpClient
  val rb = RequestBody("", MediaType.TEXT_PLAIN)
  implicit val stdHdrs = (authtoken: String) => Headers(Map("X-Auth-Token" -> authtoken))
}