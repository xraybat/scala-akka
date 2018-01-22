/**
  * Created by psc on 23/10/17.
  */

package au.com.carringbushsw.Akka.Common

import MessageTag._

// messages
sealed case class Response(msg: MessageTag)
object Response { // companion object
  val name = "Response"
}

sealed case class Request(msg: MessageTag)
object Request { // companion object
  val name = "Request"
}

// for investigator
sealed case class Search(msg: MessageTag)
object Search { // companion object
  val name = "Search"
}