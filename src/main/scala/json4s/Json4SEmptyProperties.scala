package json4s

object Json4SEmptyProperties extends App {
  import org.json4s._
  import org.json4s.native.JsonMethods._
  import org.json4s.JsonDSL._

  val map = ("foo" -> "bar") ~ ("properties" -> Nil)

  println(compact(render(map)))

}
