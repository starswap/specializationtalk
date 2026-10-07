//> using scala 3.10.0-RC1
//> using options -language:experimental.inlineTraits -Vprint:specializeInlineTraits,erasure
inline trait A[T](val x: T):
  def foo: T = x
  def matrixmultiply(): Matrix[T]

class B extends A[Int](1):
    def matrixmultiply(): Matrix[Int]
