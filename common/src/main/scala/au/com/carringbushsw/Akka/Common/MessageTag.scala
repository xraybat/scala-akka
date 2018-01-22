/**
  * Created by psc on 17/10/17.
  */

package au.com.carringbushsw.Akka.Common

object MessageTag extends Enumeration {
  type MessageTag = Value

  val doit        = Value("doit")
  val what        = Value("what?")
  val change      = Value("change")
  val request     = Value("request")
  val response    = Value("response")
  val search      = Value("search")
  val stop        = Value("stop")
  val terminate   = Value("terminate")
  
} // MessageTag