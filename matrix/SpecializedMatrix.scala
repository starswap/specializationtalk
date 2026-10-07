package specializationtalk.matrix

// Flat row-major layout: element (i,j) of an n x n matrix is at index i*n+j
inline trait SpecializedMatrix[T: {NumericSpecialized, Specialized}](val elems: Array[T], val n: Int):
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