package skunk.proto

import cats.implicits._
import cats.Semigroup
import scodec._
import scodec.bits._
import scodec.codecs._
import scodec.interop.cats._

package object message { module =>

  implicit class CodecOps[A](val self: Codec[A]) extends AnyVal {
    def applied(a: A): Codec[Unit] =
      self.encode(a).fold(fail(_), constant(_))
  }

  implicit def attemptSemigroup[A: Semigroup]: Semigroup[Attempt[A]] =
    new Semigroup[Attempt[A]] {
      def combine(a: Attempt[A], b: Attempt[A]) = (a, b).mapN(_ |+| _)
    }

  def utf8z: Codec[String] =
    (utf8 ~ constant(ByteVector(0))).xmap(_._1, (_, ()))

}