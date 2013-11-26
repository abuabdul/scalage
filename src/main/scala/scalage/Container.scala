package scalage

import java.net.URL
import spray.json._
import spray.json.DefaultJsonProtocol._
import java.io.FileInputStream

object ContainerCommand extends AbstractCommand {

  val listObjectsInContainer = (account: AccountModel, cont: String) =>
    client.get(new URL(account.storageUrl + "/" + cont + "?format=json&limit=23"),
      account.authToken).body.asString

  def createObject(objectToStore: SwiftUploadObject) = (account: AccountModel, cont: String) => {
    client.put(new URL(account.storageUrl + "/" + cont + "/" + objectToStore.name),
      rbi(new FileInputStream(objectToStore.file)), account.authToken)
  }

}

class Container(account: AccountModel, contModel: ContainerModel) {

  import scalage.ContainerCommand._

  def listItems = listObjectsInContainer(account, contModel.name).asJson.toString()
  def uploadObject(objtoStore: SwiftUploadObject): Unit = createObject(objtoStore)(account, contModel.name)
}