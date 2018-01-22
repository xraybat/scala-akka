/**
  * Created by psc on 9/6/17.
  */

package au.com.carringbushsw.Akka.ProcessManager

import akka.actor._
import akka.event._
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

// messages
sealed case class CharacterOne(msg: MessageTag)
sealed case class CharacterTwo(t: Thing)
object CharacterOne { // move into class below??
  val name  = "CharacterOne"
}
object CharacterTwo { // move into class below??
  val name  = "CharacterTwo"
}

class Thing
object AllThings {
  val bank  = new Thing()
  val wank  = new Thing()
  val crank = new Thing()
}

object ProcessManager { // companion object
  val name = "ProcessManager"
}

class ProcessManager extends MyActor {
  val log = Logging(context.system, this)

  override def preStart: Unit = {
    log.info(s"${ProcessManager.name}.preStart")
    context.become(characterOne)
  }

  override def postStop: Unit = log.info(s"${ProcessManager.name}.postStop")

  def receive = characterOne

  def characterOne: Receive = {
    case msg: String => log.info(s"${CharacterOne.name}: {msg}")  //?? never appears

    case CharacterOne(MessageTag.change) => {
      log.info(s"${ProcessManager.name}.changing to ${CharacterTwo.name}")
      context.become(characterTwo)
    }

    case msg: MessageTag => log.info(s"${CharacterOne.name}('${msg}')")

    case CharacterOne(MessageTag.doit) => log.info(s"${CharacterOne.name}('${MessageTag.doit}')")
    case CharacterOne(msg)             => log.info(s"${CharacterOne.name}('$msg')")
    case CharacterOne                  => log.info(s"${CharacterOne.name}")

    case _ => log.info(s"${ProcessManager.name}.${CharacterOne.name}.help!!")

  } // characterOne: Receive

  def characterTwo: Receive = {
    case msg: String => log.info(s"${CharacterTwo.name}: {msg}")  //?? never appears

    case msg: MessageTag => log.info(s"${CharacterTwo.name}('${msg}')")

    case CharacterTwo(b) if b == AllThings.bank => // with guard
      log.info(s"${CharacterTwo.name}(bank)")
    case CharacterTwo(w) if w == AllThings.wank => // with guard
      log.info(s"${CharacterTwo.name}(wank)")

    case CharacterTwo(_: Thing) => log.info(s"${CharacterTwo.name}(Thing)")
    case CharacterTwo           => log.info(s"${CharacterTwo.name}")

    case _ => log.info(s"${ProcessManager.name}.${CharacterTwo.name}.help!!")

  } // characterTwo: Receive
} // ProcessManager