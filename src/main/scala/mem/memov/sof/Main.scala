package mem.memov.sof

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]) =
    SofServer.stream[IO].compile.drain.as(ExitCode.Success)
}