package specializationtalk.matrix

inline trait SpecializedMatrix[T: {Specialized, NumericSpecialized}](val elems: Array[T], val n: Int):
  private val num = summon[NumericSpecialized[T]]

  def apply(i: Int, j: Int): T = elems(i * n + j)

  def matMul(other: SpecializedMatrix[T], result: Array[T]): Array[T] =
    require(this.n == other.n)
    for
      i <- 0 until n
      k <- 0 until n
    do
      val aik = this(i, k)
      for j <- 0 until n do
        result(i * n + j) = num.plus(result(i * n + j), num.times(aik, other(k, j)))
    result

inline trait NumericSpecialized[T: Specialized]:
  def fromInt(x: Int): T
  def plus(x: T, y: T): T
  def times(x: T, y: T): T

implicit object IntIsNumeric extends NumericSpecialized[Int]:
  override def fromInt(x: Int): Int = x
  override def plus(x: Int, y: Int): Int = x + y
  override def times(x: Int, y: Int): Int = x * y
