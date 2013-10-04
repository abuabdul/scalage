package scalage

import java.net.URL

import uk.co.bigbeeconsultants.http.HttpClient
import uk.co.bigbeeconsultants.http.header.Headers
import uk.co.bigbeeconsultants.http.header.MediaType
import uk.co.bigbeeconsultants.http.request.RequestBody

object AccountCommand extends AbstractCommand {

  val listAllContainers = (account: AccountModel) =>
    client.get(new URL(account.storageUrl + "?format=json"), account.authToken).body.asString

  val createNewContainer = (account: AccountModel, name: String) =>
    client.put(new URL(account.storageUrl + "/" + name), rb, account.authToken)
}

class Account(account: AccountModel) {
  import scalage.ScalageConverters._
  import scalage.AccountCommand._

  def listContainers = listAllContainers(account).containers

  def getContainer(name: String) = {
    val list = listContainers.filter(x => x.name.equals(name))
    if (list.isEmpty) None else Option(new Container(account, list.head))
  }

  def createContainer(name: String) = {
    createNewContainer(account, name)
    getContainer(name).get
  }
}