package scalage

import uk.co.bigbeeconsultants.http.request.RequestBody
import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.header.Headers
import uk.co.bigbeeconsultants.http.header.MediaType
import java.io.InputStream

trait AbstractCommand {
  val client = new HttpClient
  val rb = RequestBody("", MediaType.TEXT_PLAIN)
  val rbi = (inps: InputStream) => RequestBody(inps, MediaType.STAR_STAR)
  implicit val stdHdrs = (authtoken: String) => Headers(Map("X-Auth-Token" -> authtoken))
}