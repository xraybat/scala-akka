/**
  * Created by psc on 14/7/17.
  */

package au.com.carringbushsw.Akka.InvestigatorManager

import akka.actor._
import akka.event._
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object InvestigatorManager { // companion object
  val name = "InvestigatorManager"
}

class InvestigatorManager extends MyActor {
  val log = Logging(context.system, this)

  override def preStart: Unit = {
    log.info(s"${InvestigatorManager.name}.preStart")
    context.become(search)
  }

  override def postStop: Unit = log.info(s"${InvestigatorManager.name}.postStop")

  def receive = search

  def search: Receive = {
    case Search(MessageTag.search) => {
      log.info(s"${Search.name}('${MessageTag.search}...')")
      // search for
      // requesterManager at "akka.tcp://TestAkkaSystem@127.0.0.1:2552/user/RequesterManager"
      // responderManager at "akka.tcp://ResponderAkkaSystem@127.0.0.1:2553/user/ResponderManager"
      TheFatController.requesterManager ! Request(MessageTag.request)
    }

    case _ => log.info(s"${InvestigatorManager.name}.${Search.name}.help!!")

  } // search: Receive
} // InvestigatorManager
