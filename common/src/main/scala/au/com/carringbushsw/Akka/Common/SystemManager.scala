/**
  * Created by psc on 23/10/17.
  */

package au.com.carringbushsw.Akka.Common

import akka.actor._
import akka.event._
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._
import MessageTag._

// messages
sealed case class System(msg: MessageTag, sys: ActorSystem = null, actor: ActorRef = null) // should be `Option[ActorRef]`, yes??

object SystemManager { // companion object
  val name = "SystemManager"
}

class SystemManager extends MyActor {
  val log = Logging(context.system, this)

  override def preStart: Unit = {
    log.info (s"${SystemManager.name}.preStart")
    context.become(systemManager)
  }

  override def postStop: Unit = log.info(s"${SystemManager.name}.postStop")

  def receive = systemManager

  def systemManager: Receive = {
    case msg: String => log.info(s"${msg}")

    case System(MessageTag.stop, sys, actor) => {
      assert(sys != null, "must supply a system")
      assert(actor != null, "must supply an actor")
      log.info(s"${SystemManager.name}(sys, actor) :: ${MessageTag.stop} actor...")
      sys.stop(actor)
    }

    case System(MessageTag.terminate, sys, _) => {
      assert(sys != null, "must supply a system")
      log.info(s"${SystemManager.name}(sys) :: ${MessageTag.terminate} system...")
      sys.terminate.wait
      // or...
      //   `sys.terminate``
      // somewhere else in your code...
      //   `sys.whenTerminated.wait`
    }

    case _ => log.info(s"${SystemManager.name}.help!!")

  } // systemManager: Receive
} // SystemManager