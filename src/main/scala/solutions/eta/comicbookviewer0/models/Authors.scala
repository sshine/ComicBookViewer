package solutions.eta.comicbookviewer0

import cats.implicits._
import cats.Applicative
import cats.Functor
import cats.effect.IO
import cats.effect.Sync
import io.circe.generic.semiauto.deriveCodec
import io.circe.Codec
import java.util.UUID
import org.http4s.{EntityDecoder, EntityEncoder, HttpRoutes}
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.circe.CirceEntityCodec._
import org.http4s.dsl.Http4sDsl

object Authors {

  final case class Author(id: Int, name: String, email: String, slug: String)
  object Author {
    implicit val codec: Codec[Author] = deriveCodec[Author]
    implicit def entityDecoder[F[_]: Sync]: EntityDecoder[F, Author] = jsonOf
    implicit def entityEncoder[F[_]: Applicative]: EntityEncoder[F, Author] =
      jsonEncoderOf
  }

  final case class New(name: String, email: String, slug: String)
  object New {
    implicit val codec: Codec[New] = deriveCodec[New]
    implicit def entityDecoder[F[_]: Sync]: EntityDecoder[F, New] = jsonOf
    implicit def entityEncoder[F[_]: Applicative]: EntityEncoder[F, New] =
      jsonEncoderOf
  }

  final case class Id(id: Int)
  object Id {
    implicit val codec: Codec[Id] = deriveCodec[Id]
    implicit def entityDecoder[F[_]: Sync]: EntityDecoder[F, Id] = jsonOf
    implicit def entityEncoder[F[_]: Applicative]: EntityEncoder[F, Id] =
      jsonEncoderOf
  }

  final case class Slug(slug: String)
  object Slug {
    implicit val codec: Codec[Slug] = deriveCodec[Slug]
    implicit def entityDecoder[F[_]: Sync]: EntityDecoder[F, Slug] = jsonOf
    implicit def entityEncoder[F[_]: Applicative]: EntityEncoder[F, Slug] =
      jsonEncoderOf
  }

}
