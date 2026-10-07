package specializationtalk.matrix

// Flat row-major layout: element (i,j) of an n x n matrix is at index i*n+j
class GenericMatrix[T: Numeric](val elems: Array[T], val n: Int):
  private val num = summon[Numeric[T]]

  def apply(i: Int, j: Int): T = elems(i * n + j)

  def matMul(other: GenericMatrix[T], result: Array[T]): GenericMatrix[T] =
    require(this.n == other.n)
    for
      i <- 0 until n
      k <- 0 until n
    do
      val aik = this(i, k)
      for j <- 0 until n do
        result(i * n + j) = num.plus(result(i * n + j), num.times(aik, other(k, j)))
    GenericMatrix[T](result, n)
