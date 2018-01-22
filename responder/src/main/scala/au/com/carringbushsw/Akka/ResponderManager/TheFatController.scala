/**
  * Created by psc on 28/6/17.
  */
//@TODO: remove all FatControllers from everything except system/ProcessManager project/class??

package au.com.carringbushsw.Akka.ResponderManager

import akka.actor._
import akka.event._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

// duplicate object??
object TheFatController { // 'thomas the tank engine'
  private val name = "ResponderAkkaSystem"
  private val system = ActorSystem(s"${name}")

  // local, private
  private val systemManager: ActorRef = system.actorOf(Props[SystemManager], s"${SystemManager.name}")
  systemManager ! "this system's manager is alive..."

  // local, public
  val responderManager: ActorRef = system.actorOf(Props[ResponderManager], name = s"${ResponderManager.name}")
  responderManager ! "the responder actor is alive..."

  // remote
  // don't need to know RequesterManager as we just use `sender !`

  def stop: Unit = {
    systemManager ! System(MessageTag.terminate, system)
  }
} // TheFatController
