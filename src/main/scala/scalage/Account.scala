package scalage

import java.net.URL

import scalage.ScalageConverters.toContainers.apply
import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.header.Headers
import uk.co.bigbeeconsultants.http.header.MediaType
import uk.co.bigbeeconsultants.http.request.RequestBody

trait AbstractCommand {
  val client = new HttpClient
  val rb = RequestBody("", MediaType.TEXT_PLAIN)
  implicit val stdHdrs = (authtoken: String) => Headers(Map("X-Auth-Token" -> authtoken))
}

object AccountCommand extends AbstractCommand {

  val listAllContainers = (account: AccountModel) =>
    client.get(new URL(account.storageUrl + "?format=json"), account.authToken).body.asString

  val createNewContainer = (account: AccountModel, name: String) =>
    client.put(new URL(account.storageUrl + "/" + name), rb, account.authToken)
}

class Account(account: AccountModel) {
  import scalage.ScalageConverters._
  import scalage.AccountCommand._

  def getAccount = account
  
  def listContainers = listAllContainers(account).containers
  
  def getContainer(name: String) = {
    val list = listContainers.filter(x => x.name.equals(name))
    if (list.isEmpty) None else Option(list.head)
  }
  
  def createContainer(name: String) = createNewContainer(account, name)
}