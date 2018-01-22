/**
  * Created by psc on 24/10/17.
  */
//@TODO: remove all FatControllers from everything except system/ProcessManager project/class??
package au.com.carringbushsw.Akka.RequesterManager

import akka.actor._
import akka.event._
import com.typesafe.config._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object TheFatController { // 'thomas the tank engine'
  private val name = "RequesterAkkaSystem"
  private val system = ActorSystem(s"${name}")

  // local, private
  private val systemManager: ActorRef = system.actorOf(Props[SystemManager], name = s"${SystemManager.name}")
  systemManager ! "this system's manager is alive..."

  // local, public
  val requesterManager: ActorRef = system.actorOf(Props[RequesterManager], name = s"${RequesterManager.name}")
  requesterManager ! "the requester actor is alive..."

  // remote, public
  val responderManager = system.actorSelection("akka.tcp://ResponderAkkaSystem@127.0.0.1:5153/user/ResponderManager")
  requesterManager ! "have connected to the responder actor..."

  def stop: Unit = {
    systemManager ! System(MessageTag.terminate, system)
  }
} // TheFatController
