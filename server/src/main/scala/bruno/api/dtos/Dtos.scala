package bruno.api.dtos

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

sealed trait Dtos

object Dtos extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val signupFormat = jsonFormat8(SignupNotificationDto)
  implicit val finalNotificationFormat = jsonFormat2(FinalNotificationDto)
}

case class SignupNotificationDto (
  email: String,
  reference: Option[Long] = None,
  gender: String,
  initials: Option[String],
  prefixes: Option[String] = None,
  firstName: Option[String] = None,
  lastName: String,
  phone: Option[String]
) extends Dtos

case class FinalNotificationDto (
  fullName: String, // FirstName + LastName
  timestamp: Long
) extends Dtos
