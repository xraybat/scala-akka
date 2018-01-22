/**
  * Created by psc on 24/10/17.
  */

package au.com.carringbushsw.Akka.RequesterManager

import akka.actor._
import akka.event._
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object RequesterManager { // companion object
  val name = "RequesterManager"
}

class RequesterManager extends MyActor {
  val log = Logging(context.system, this)
  var counter = 0

  override def preStart: Unit = {
    log.info(s"${RequesterManager.name}.preStart")
    context.become(request)
  }

  override def postStop: Unit = log.info(s"${RequesterManager.name}.postStop")

  def receive = request

  def request: Receive = {
    // request-and-response
    case "A REQUEST" => {
      log.info("sending A REQUEST...")
      TheFatController.responderManager ! "A REQUEST"
    }
    case "the local actor is alive..." => {
      log.info("the local actor is alive...")
      // send first test message to responder here....
      log.info("sending A REQUEST...")
      TheFatController.responderManager ! "A REQUEST"
    }
    case msg: String => log.info(s"${msg}")

    case Request(MessageTag.request) => {
      if (counter < 5) {
        log.info(s"${Request.name}('${MessageTag.request}...')")
        TheFatController.responderManager ! Request(MessageTag.request)
        counter += 1
      }
      else
        log.info(s"${Request.name}('sent as many messages as i'm going to')")
    }
    case Response(MessageTag.response) => {
      log.info(s"${Response.name}('received ${MessageTag.response}')")

      // finish up here hopefully
      //@TODO:check
      //TheFatController.stop
    }

    case _ => log.info(s"${RequesterManager.name}.${Request.name}.help!!")

  } // request: Receive
} // RequesterManager