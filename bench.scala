package specializationtalk

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.infra.Blackhole

import specializationtalk.matrix.* 

// Generate two 300 x 300 square matrices and multiply them together.
// We use 3 warm up iterations and 3 measurement iterations to get faster results for the presentation.

@State(Scope.Benchmark)
class Data:
  val n = 300

  val mat1values = Array.fill(n * n)(math.round(math.random() * 100).toInt)
  val mat2values = Array.fill(n * n)(math.round(math.random() * 100).toInt)

  val result = Array.ofDim[Int](n * n)

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.SECONDS)
@Fork(1)
class MatBench:
  ???
  // @Benchmark
  // def manual(m: Data, blackHole: Blackhole) =
  //   val x1 = IntMatrix(m.mat1values, m.n)
  //   val y1 = IntMatrix(m.mat2values, m.n)
  //   blackHole.consume(x1.matMul(y1, m.result))

  // @Benchmark
  // def generic(m: Data, blackHole: Blackhole) =
  //   val x1 = GenericMatrix[Int](m.mat1values, m.n)
  //   val y1 = GenericMatrix[Int](m.mat2values, m.n)
  //   blackHole.consume(x1.matMul(y1, m.result))

  // @Benchmark
  // def specialized(m: Data, blackHole: Blackhole) =
  //   val x1 = new SpecializedMatrix[Int](m.mat1values, m.n) {}
  //   val y1 = new SpecializedMatrix[Int](m.mat2values, m.n) {}
  //   blackHole.consume(x1.matMul(y1, m.result))
