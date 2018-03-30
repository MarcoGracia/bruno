package bruno.api.dtos

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

sealed trait Dtos

object Dtos extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val signupFormat = jsonFormat9(SignupNotificationDto)
  implicit val finalNotificationFormat = jsonFormat3(FinalNotificationDto)
}

case class SignupNotificationDto (
  email: String,
  reference: Option[Long] = None,
  gender: Option[String],
  initials: Option[String],
  prefixes: Option[String] = None,
  firstName: Option[String] = None,
  lastName: String,
  phone: Option[String],
  city: String
) extends Dtos

case class FinalNotificationDto (
  fullName: String, // FirstName + LastName
  city: String,
  timestamp: Long
) extends Dtos
