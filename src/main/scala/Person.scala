


object TestAp1 extends App {

  /*class IO[A] private (constructorCodeBlock: => A) {

    def run = constructorCodeBlock

    def flatMapOrig[B](f: A => IO[B]): IO[B] = IO(f(run).run)

    def flatMap[B](customAlgorithm: A => IO[B]): IO[B] = {
      val result1: IO[B] = customAlgorithm(run)
      println(s"here result1 is ${result1.run}")
      val result2: B = result1.run
      println(s"result2 is $result2")
      IO(result2)
    }

    def map[B](f: A => B): IO[B] = flatMap(a => IO(f(a)))

  }

  object IO {
    def apply[A](a: => A): IO[A] = new IO(a)
  }


  def getLine: IO[String] = IO(scala.io.StdIn.readLine())
  def putStrLn(s: String): IO[Unit] = IO(println(s))

  /* for {
    _ <- putStrLn("Enter ur name")
    name <- getLine
    _ <- putStrLn(s"What a shitty name $name")
    msg <- getLine
  } yield msg */

  putStrLn("Enter ur name").flatMap {
    _ => getLine.flatMap {
      name => putStrLn(name)
    }
  }*/



}


