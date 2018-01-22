/**
  * Created by psc on 14/7/17.
  */

import au.com.carringbushsw.Akka.InvestigatorManager._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object Main extends App {
  val tfc = TheFatController

  tfc.investigatorManager ! Search(MessageTag.search)

  // finish up here after delay
  Thread.sleep(30000)
  tfc.stop

} // Main