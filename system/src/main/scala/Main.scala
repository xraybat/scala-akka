/**
  * Created by psc on 4/6/17.
  */

import au.com.carringbushsw.Akka.ProcessManager._
import au.com.carringbushsw.Akka.Common._
import au.com.carringbushsw.Akka.Common.MessageTag._

object Main extends App {
  val tfc = TheFatController

  // send message to actor ref using `!`
  tfc.processManager ! MessageTag.doit
  tfc.processManager ! MessageTag.what

  tfc.processManager ! CharacterOne(MessageTag.doit)
  tfc.processManager ! CharacterOne(MessageTag.what)
  tfc.processManager ! CharacterOne
  tfc.processManager ! CharacterOne(MessageTag.change)

  tfc.processManager ! MessageTag.what  // deliberate mistake
  tfc.processManager ! CharacterTwo(AllThings.bank)
  tfc.processManager ! CharacterTwo(AllThings.wank)
  tfc.processManager ! CharacterTwo(AllThings.crank)
  tfc.processManager ! CharacterTwo

  tfc.requesterManager ! "A REQUEST"
  tfc.requesterManager ! Request(MessageTag.request)

  // finish up here after delay and not stopped elsewhere
  Thread.sleep(30000)
  tfc.stop

} // Main