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
import org.pure4s.uuid4s.FUUID

trait Comics[F[_]] {
  def getAuthor(author: Authors.Slug): F[Option[Authors.Author]]

  def getComic(
      authorSlug: Authors.Slug,
      comicSlug: Comics.Slug
  ): F[Option[Comics.Comic]]

  def getComicPage(
      authorSlug: Authors.Slug,
      comicSlug: Comics.Slug,
      pagePubId: ComicPages.PubId
  ): F[Option[ComicPages.ComicPage]]
}

object Comics {
  def apply[F[_]](implicit comics: Comics[F]): Comics[F] = comics

  final case class Comic(
      id: Int,
      authorId: Int,
      slug: String,
      title: String,
      pages: List[ComicPages.ComicPage]
  )
  object Comic {
    implicit val codec: Codec[Comic] = deriveCodec[Comic]
    implicit def entityDecoder[F[_]: Sync]: EntityDecoder[F, Comic] = jsonOf
    implicit def entityEncoder[F[_]: Applicative]: EntityEncoder[F, Comic] =
      jsonEncoderOf
  }

  final case class New(authorId: Authors.Id, slug: Comics.Slug, title: String)
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

  def impl[F[_]: Applicative]: Comics[F] = new Comics[F] {
    def getAuthor(slug: Authors.Slug): F[Option[Authors.Author]] =
      none.pure[F]

    def getComic(
        authorSlug: Authors.Slug,
        comicSlug: Comics.Slug
    ): F[Option[Comics.Comic]] =
      none.pure[F]

    def getComicPage(
        slug: Authors.Slug,
        comicId: Comics.Slug,
        pagePubId: ComicPages.PubId
    ): F[Option[ComicPages.ComicPage]] =
      none.pure[F]
  }

  def routes[F[_]: Sync](comics: Comics[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "comics" / authorSlug =>
        comics.getAuthor(Authors.Slug(authorSlug)).flatMap {
          case Some(author) => Ok(author)
          case None         => NotFound()
        }
      case GET -> Root / "comics" / authorSlug / comicSlug =>
        comics
          .getComic(Authors.Slug(authorSlug), Comics.Slug(comicSlug))
          .flatMap {
            case Some(comic) => Ok(comic)
            case None        => NotFound()
          }
      case GET -> Root / "comics" / authorSlug / comicSlug / pagePubId =>
        comics
          .getComicPage(
            Authors.Slug(authorSlug),
            Comics.Slug(comicSlug),
            ComicPages.PubId(pagePubId)
          )
          .flatMap {
            case Some(comicPage) => Ok(comicPage)
            case None            => NotFound()
          }
    }
  }

}
