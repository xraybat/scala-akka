/**
  * Created by psc on 14/7/17.
  */
//@TODO: remove all FatControllers from everything except system/ProcessManager project/class??

package au.com.carringbushsw.Akka.InvestigatorManager

import akka.actor._
import akka.event._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object TheFatController { // 'thomas the tank engine'
  private val name = "InvestigatorAkkaSystem"
  private val system = ActorSystem(s"${name}")
  private val systemManager: ActorRef = system.actorOf(Props[SystemManager], s"${SystemManager.name}")
  systemManager ! "this system's manager is alive..."

  val investigatorManager: ActorRef = system.actorOf(Props[InvestigatorManager], s"${InvestigatorManager.name}")
  investigatorManager ! "the investigator actor is alive..."

  val requesterManager = system.actorSelection("akka.tcp://TestAkkaSystem@127.0.0.1:5152/user/RequesterManager")
  investigatorManager ! "have connected to the requester actor..."
  
  val responderManager = system.actorSelection("akka.tcp://ResponderAkkaSystem@127.0.0.1:5153/user/ResponderManager")
  investigatorManager ! "have connected to the responder actor..."

  def stop: Unit = {
    systemManager ! System(MessageTag.terminate, system)
  }
} // TheFatController
