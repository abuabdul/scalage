package prassee

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import com.twitter.finagle.Service
import org.jboss.netty.handler.codec.http.HttpRequest
import org.jboss.netty.handler.codec.http.HttpResponse
import org.jboss.netty.handler.codec.http.DefaultHttpResponse
import org.jboss.netty.handler.codec.http.HttpVersion
import org.jboss.netty.handler.codec.http.HttpResponseStatus
import com.twitter.util.Future
import java.net.InetSocketAddress
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.Http

object App {
  def main(args: Array[String]) {

    val service = new Service[HttpRequest, HttpResponse] {
      def apply(req: HttpRequest) = {
        val r = req.getUri() match {
          case "/" => {
            println("init actor")
            val actorRef = ActorSystem().actorOf(Props(new FirstActor()))
            actorRef.!("ping")
            new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK)
          }
        }
        Future.value(r)
      }
    }

    val inet = new InetSocketAddress(10000)
    ServerBuilder().codec(Http()).bindTo(inet).name("logingestorService").build(service)
  }
}

class FirstActor extends Actor {
  def receive = {
    case "ping" => {
      println("pong")
    }
  }
}
