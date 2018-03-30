package bruno.api.clients

import akka.actor.ActorSystem
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.{Http, HttpExt}
import akka.stream.Materializer
import bruno.api.dtos.{FinalNotificationDto, SignupNotificationDto}

import scala.concurrent.ExecutionContext

trait AppClient extends RequestBuilding {
  def sendNotification(notification: FinalNotificationDto)
}

class AppClientImpl(implicit val executionContext: ExecutionContext, val system: ActorSystem, val mat: Materializer) extends AppClient {

  lazy val http: HttpExt = Http()
  def BaseUrl: String = "https://brunopusher.localtunnel.me"

  override def sendNotification(notification: FinalNotificationDto)= {
    println("Sending notification", notification)
    http.singleRequest(Post(s"$BaseUrl/signup", notification)).map{ response =>
      response.status match {
        case StatusCodes.OK => println("GREAT SUCCESS")
        case _ => println("ERROR")
      }
    }
  }
}
