enum Regexp {
  case Append(left: Regexp, right: Regexp)
  case OrElse(first: Regexp, second: Regexp)
  case Repeat(source: Regexp)
  case Apply(string: String)
  case Empty

  def ++(that: Regexp): Regexp = Append(this, that)
  def orElse(that: Regexp): Regexp = OrElse(this, that)
  def repeat: Regexp = Repeat(this)
  def `*` : Regexp = this.repeat

  def matches(input: String): Boolean =
    def loop(regexp: Regexp, idx: Int): Option[Int] =
      regexp match {
        case Append(left, right) =>
          loop(left, idx).flatMap(i => loop(right, i))
        case OrElse(first, second) =>
          loop(first, idx).orElse(loop(second, idx))
        case Repeat(source) =>
          loop(source, idx).flatMap { i => loop(source, i) }.orElse(Some(idx))
        case Apply(string) =>
          Option.when(input.startsWith(string, idx))(idx + string.length)
        case Empty => None
      }

    loop(this, 0).contains(input.length)
}
object Regexp {
  val empty: Regexp = Empty

  def apply(string: String): Regexp = Apply(string)
}

@main def run() =
  val regexp = Regexp.apply("Scala").`*`
  assert(regexp.matches("ScalaScala"))
