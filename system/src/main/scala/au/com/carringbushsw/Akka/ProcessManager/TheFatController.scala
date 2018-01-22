/**
  * Created by psc on 9/6/17.
  */
//@TODO: remove all FatControllers from everything except system/ProcessManager project/class??

package au.com.carringbushsw.Akka.ProcessManager

import akka.actor._
import akka.event._
import com.typesafe.config._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object TheFatController { // 'thomas the tank engine'
  private val name = "TestAkkaSystem"
  private val system = ActorSystem(s"${name}")

  // local, private
  private val systemManager: ActorRef = system.actorOf(Props[SystemManager], name = s"${SystemManager.name}")
  systemManager ! "this system's manager is alive..."

  // local, public
  val processManager: ActorRef = system.actorOf(Props[ProcessManager], name = s"${ProcessManager.name}")        // either, create actor and get its actor ref...
  processManager ! "the process manager is alive..."  //?? never appears

  // remote, public
  val requesterManager = system.actorSelection("akka.tcp://RequesterAkkaSystem@127.0.0.1:5152/user/RequesterManager")
  processManager ! "have connected to the requester actor..."

  val responderManager = system.actorSelection("akka.tcp://ResponderAkkaSystem@127.0.0.1:5153/user/ResponderManager")
  processManager ! "have connected to the responder actor..."

  def stop: Unit = {
    systemManager ! System(MessageTag.terminate, system)
  }
} // TheFatController