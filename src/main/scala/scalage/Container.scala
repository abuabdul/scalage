package scalage

import java.net.URL

object ContainerCommand extends AbstractCommand {

  val listObjectsInContainer = (account: AccountModel, cont: String) =>
    client.get(new URL(account.storageUrl + "/" + cont + "?format=json"),
      account.authToken).body.asString
}

class Container(account: AccountModel, contModel: ContainerModel) {

  import scalage.ContainerCommand._
  def listItems = listObjectsInContainer(account, contModel.name)
}