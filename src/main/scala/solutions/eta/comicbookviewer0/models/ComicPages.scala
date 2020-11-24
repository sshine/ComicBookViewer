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

object ComicPages {

  final case class ComicPage(id: Int, comicId: Int, pubId: UUID)
  object ComicPage {
    implicit val codec: Codec[ComicPage] = deriveCodec[ComicPage]
    implicit def entityDecoder[F[_]: Sync]: EntityDecoder[F, ComicPage] = jsonOf
    implicit def entityEncoder[F[_]: Applicative]: EntityEncoder[F, ComicPage] =
      jsonEncoderOf
  }

  final case class New(comicId: Int, pubId: UUID)
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

  // TODO: Change 'String' to 'FUUID'!
  final case class PubId(pubId: String)
  object PubId {
    implicit val codec: Codec[PubId] = deriveCodec[PubId]
    implicit def entityDecoder[F[_]: Sync]: EntityDecoder[F, PubId] = jsonOf
    implicit def entityEncoder[F[_]: Applicative]: EntityEncoder[F, PubId] =
      jsonEncoderOf
  }

}
