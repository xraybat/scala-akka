/**
  * Created by psc on 24/10/17.
  */

import au.com.carringbushsw.Akka.RequesterManager._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object Main extends App {
  val tfc = TheFatController

  tfc.requesterManager ! "A REQUEST"
  tfc.requesterManager ! Request(MessageTag.request)

  // finish up here after delay and not stopped elsewhere
  Thread.sleep(30000)
  tfc.stop

} // Main