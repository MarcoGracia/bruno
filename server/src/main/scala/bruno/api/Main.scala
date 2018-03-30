package bruno.api

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import bruno.api.routes.BrunoRoute

object Main extends App with BrunoRoute {
  Http().bindAndHandle(brunoRoute, "0.0.0.0", 9100).transform(
    binding => println(s"REST interface bound to ${binding.localAddress} "), { t => println(s"Couldn't bind interface: ${t.getMessage}", t); sys.exit(1) }
  )
}
