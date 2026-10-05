//> using scala 3.10.0-RC1
//> using options -language:experimental.inlineTraits -Vprint:specializeInlineTraits

inline trait A[T](val x: T):
    def foo: T = x

class B extends A[Int](1)
