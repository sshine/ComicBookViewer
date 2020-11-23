package solutions.eta.comicbookviewer0

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]) =
    Comicbookviewer0Server.stream[IO].compile.drain.as(ExitCode.Success)
}