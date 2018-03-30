package bruno.api.actors

import java.time.Instant

import akka.actor.{Actor, ActorSystem, Props}
import akka.stream.ActorMaterializer
import bruno.api.clients.{AppClient, AppClientImpl}
import bruno.api.dtos.{FinalNotificationDto, SignupNotificationDto}

import scala.concurrent.ExecutionContext


object BrunoActor {
  def props(appClient: AppClientImpl)(implicit materializer: ActorMaterializer, executionContext: ExecutionContext, system: ActorSystem) = Props(classOf[BrunoActor], appClient, materializer, executionContext, system)
}

class BrunoActor(appClient: AppClientImpl)(implicit materializer: ActorMaterializer, executionContext: ExecutionContext, system: ActorSystem) extends Actor {

  private var subscribedDevices: List[String] = Nil

  override def receive: Receive = {
    case signup: SignupNotificationDto =>
      val finalNotification = FinalNotificationDto(s"${signup.firstName.getOrElse("")} ${signup.lastName}", signup.city, Instant.now().getEpochSecond)
      appClient.sendNotification(finalNotification)

  }
}
