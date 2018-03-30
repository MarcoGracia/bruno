package bruno.api.routes


import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.server.Directives
import akka.stream.{ActorMaterializer, Materializer}
import akka.util.Timeout
import bruno.api.actors.BrunoActor
import bruno.api.clients.{AppClient, AppClientImpl}
import bruno.api.dtos._
import spray.json.DefaultJsonProtocol

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

trait BrunoRoute extends Directives with DefaultJsonProtocol {
  implicit val system: ActorSystem = ActorSystem()

  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  implicit val actorMaterializer: ActorMaterializer = ActorMaterializer.create(system)

  val brunoActor = system.actorOf(BrunoActor.props(new AppClientImpl), name = "BrunoActor")

  private implicit val timeout = Timeout(10.seconds)

  val brunoRoute = pathPrefix("api") {
    pathPrefix("push" / "broadcast") {
      post {
        entity(as[SignupNotificationDto]) { notification =>
          complete {
            brunoActor ! notification
            Future.successful(notification)
          }
        }
      }
    }
  }
}
