// Flat row-major layout: element (i,j) of an n x n matrix is at index i*n+j
class IntMatrix(val elems: Array[Int], val n: Int):
  def apply(i: Int, j: Int): Int = elems(i * n + j)

  def matMul(other: IntMatrix, result: Array[Int]): IntMatrix =
    require(this.n == other.n)
    for
      i <- 0 until n
      k <- 0 until n
    do
      val aik = this(i, k)
      for j <- 0 until n do
        result(i * n + j) += aik * other(k, j)
    IntMatrix(result, n)
