/**
  * Created by psc on 28/6/17.
  */

import au.com.carringbushsw.Akka.ResponderManager._

object Main extends App {
  val tfc = TheFatController

  // send message to actor ref using `!`
  //tfc.responderManager ! Request(MessageTag.request)

  // finish up here after delay and not stopped elsewhere
  Thread.sleep(30000)
  tfc.stop

} // Main