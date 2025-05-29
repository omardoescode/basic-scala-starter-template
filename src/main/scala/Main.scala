import cats.*
import cats.syntax.all.*

case class Person(name: String, age: Int)
object Person {
  given Show[Person] with
    def show(person: Person): String =
      s"${person.name} is ${person.age} years old"
}

@main def run() =
  println(Person("Omar", 20).show)
