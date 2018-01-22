/**
  * Created by psc on 9/6/17.
  */

package au.com.carringbushsw.Akka.ResponderManager

import akka.actor._
import akka.event._
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object ResponderManager { // companion object
  val name = "ResponderManager"
}

class ResponderManager extends MyActor {
  val log = Logging(context.system, this)

  override def preStart: Unit = {
    log.info(s"${ResponderManager.name}.preStart")
    context.become(response)
  }

  override def postStop: Unit = log.info(s"${ResponderManager.name}.postStop")

  def receive = response

  def response: Receive = {
    // request-and-response
    case "A REQUEST" => log.info("received A REQUEST")
    case msg: String => log.info(s"${msg}")

    case Request(MessageTag.request) => {
      val s = sender  // get sender before any context change

      log.info(s"${Request.name}('received ${MessageTag.request}')")
      log.info(s"sending ${Response.name}('${MessageTag.response}...')")
      s ! Response(MessageTag.response) // send response back to requester
    }

    case _ => log.info(s"${ResponderManager.name}.${Response.name}.help!!")

  } // response: Receive
} // ResponderManager