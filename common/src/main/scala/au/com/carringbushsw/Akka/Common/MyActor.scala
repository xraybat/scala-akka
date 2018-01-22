/**
  * Created by psc on 17/10/17.
  */

package au.com.carringbushsw.Akka.Common

import akka.actor._
import akka.event._
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._

abstract class MyActor extends Actor {
  override val supervisorStrategy =
    OneForOneStrategy(
      maxNrOfRetries = 5, withinTimeRange = Duration.create("1 minute")) {
      case _: NullPointerException          => Restart
      case _: ArithmeticException           => Resume
      case _: IllegalArgumentException      => Stop
      case _: UnsupportedOperationException => Stop
      case _: Exception                     => Escalate
    }
} // MyActor