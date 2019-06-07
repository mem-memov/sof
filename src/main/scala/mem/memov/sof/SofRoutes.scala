package mem.memov.sof

import cats.effect.Sync
import cats.implicits._
import org.http4s.{HttpRoutes, ParseFailure}
import org.http4s.dsl.Http4sDsl

object SofRoutes {

  def jokeRoutes[F[_]: Sync](J: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- J.get
          resp <- Ok(joke)
        } yield resp
    }
  }

  def helloWorldRoutes[F[_]: Sync](H: HelloWorld[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._

    case class Foo(i: Int)

    object TagQueryParamMatcher extends OptionalMultiQueryParamDecoderMatcher[String]("tag")

    HttpRoutes.of[F] {
      case GET -> Root / "hello" / name =>
        for {
          greeting <- H.hello(HelloWorld.Name(name))
          resp <- Ok(greeting)
        } yield resp
      case GET -> Root / "search" :? TagQueryParamMatcher(cats.data.Validated.Valid(tag)) =>
        tag match {
          case Nil =>
            Ok("netu")
          case tags =>
            Ok("est: " + tags.toString())
        }
    }
  }
}