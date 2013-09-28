package prassee

import uk.co.bigbeeconsultants.http.HttpClient
import java.net.URL

object App {
  def main(args: Array[String]) {
    val client = new HttpClient
    val resp = client.get(new URL("http://www.prassee.com"))
    println(resp.body.asString)
  }
}

