
trait Printable[A] {
  def format(value: A): String
}

final case class Cat(name: String, age: Int, color: String)

object PrintableInstances {
  implicit val intPrintable: Printable[Int]
    = new Printable[Int] {
      override def format(value: Int): String = value.toString
  }

  implicit val stringPrintable: Printable[String]
    = new Printable[String] {
      override def format(value: String): String = value
    }


}

object Printable {
  def format[A](value: A)(implicit printer: Printable[A]): String = printer.format(value)
  def print[A](value: A)(implicit printer: Printable[A]): Unit = println(printer.format(value))
}

import PrintableInstances._
val cat = Cat("Sdf", 123, "sdfdsf");
implicit val catPrintable: Printable[Cat]
= new Printable[Cat] {
  override def format(value: Cat) = s"""${Printable.format(value.name)} is a ${Printable.format(value.age)} year-old ${Printable.format(value.color)} cat"""
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit printer: Printable[A]) = printer.format(value)
  }
}
import Printable._
format(cat)
import PrintableSyntax._
cat.format

val x = Map(
  1 -> List("test"),
  2 -> List("sdf")
).withDefault(Nil)

Nil(3)
