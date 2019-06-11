package v1.post

import scala.concurrent.Future

final case class TestData(id: TestId, firstName: String, lastName: String )

class TestId private (val underlying: Int) extends AnyVal {
  override def toString: String = underlying.toString
}

object TestId {
  def apply(raw:String):  TestId = {
    require(raw != null)
    new TestId(Integer.parseInt(raw))

  }
}

trait TestRepository {
  def create (data: TestData)(implicit markerContext: RequestMarkerContext): Future[TestId]
  def list()(implicit markerContext: RequestMarkerContext): Future[Iterable[TestData]]
  def getById(id: TestId)(implicit markerContext: RequestMarkerContext): Future[Option[TestData]]
}