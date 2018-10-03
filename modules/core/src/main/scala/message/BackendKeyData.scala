package skunk
package proto
package message

import scodec.Decoder
import scodec.codecs._

/**
 * Cancellation key data. The frontend must save these values if it wishes to be able to issue
 * `CancelRequest` messages later.
 * @param pid The process ID of this backend.
 * @param key The secret key of this backend.
 */
final case class BackendKeyData(pid: Int, key: Int) extends BackendMessage

object BackendKeyData {
  final val Tag = 'K'
  val decoder: Decoder[BackendMessage] = (int32 ~ int32).map(BackendKeyData(_, _))
}