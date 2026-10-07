//> using scala 3.10.0-RC1
//> using options -language:experimental.specializedTraits -Vprint:erasure

inline trait Foo[T: Specialized](x: T):
  def g: T = x

def f(b: Foo[Int]) = 37 + b.g

def main(args: Array[String]): Unit =
  val x = new Foo[Int](42) {}
  val y = f(x)
