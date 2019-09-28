/*
  Simple Json AST
 */
sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsArray(get: List[Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Int) extends Json
case object JsNull extends Json


// Type class
trait JsonWriter[A] {
  def write(value: A): Json
}

// Type class instances - provide implementations for the types we care about

final case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String]
    = new JsonWriter[String] {
    def write(value: String): Json = JsString(value)
  }

  implicit val personWriter: JsonWriter[Person]
    = new JsonWriter[Person] {
    override def write(value: Person) =
      JsObject(Map(
        "name" -> JsString(value.name),
        "email" -> JsString(value.email)
      ))
  }

  /*
    Why def - implicit methods are used to construct
    instances from other type class instances
    Instead of writing separate implicit vals for intOptionWrite, stringOptionWriter and so on
    we will end up requiring two implicit vals for every A - A, option[A]
   */
  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]]
    = new JsonWriter[Option[A]] {
    override def write(value: Option[A]) = value match {
      case Some(aValue) => writer.write(aValue)
      case None => JsNull
    }
  }

}

/*
  Type class interfaces - They are generic methods, which we expose to users, which takes
  instances of type class as implicit parameters
  We can specify interface in two ways
   - Interface Objects
   - Interface syntax - uses extension methods
 */
// Interface Objects
object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json = w.write(value)
}

  // Example of how to use them
  import JsonWriterInstances._
  Json.toJson(Person(name = "harish", email = "harisht@udel.edu"))
  Json.toJson(Option(Person(name = "harish", email = "harisht@udel.edu")))

// Interface syntax
object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
  }
}
  // Example of how to use them
  import JsonSyntax._
  import JsonWriterInstances._
Person(name = "harish", email = "harisht@udel.edu").toJson



