package com.evolutionnext.everysevendays

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should
import java.time._
import java.time.temporal.ChronoUnit._
import java.time.temporal._

import scala.language.implicitConversions

class EverySevenDaysSpec extends AnyFunSpec with should.Matchers {
  describe("Calculate something every seven days") {
    it("should be done with the following") {

        implicit val convertInstantToLocalDate: Instant => LocalDate =
            LocalDate.ofInstant(_, ZoneId.systemDefault())
        implicit val convertInstantToLocalDateTime: Instant => LocalDateTime =
            LocalDateTime.ofInstant(_, ZoneId.systemDefault())
        implicit val convertInstantToZonedDateTime: Instant => ZonedDateTime =
            ZonedDateTime.ofInstant(_, ZoneId.systemDefault())

        implicit val convertLocalDateToInstant: LocalDate => Instant =
            _.atStartOfDay(ZoneId.systemDefault()).toInstant();
        implicit val convertLocalDateTimeToInstant: LocalDateTime => Instant =
            Instant.from
        implicit val convertZonedDateTimeToInstant: ZonedDateTime => Instant =
            Instant.from


      implicit val orderingLocalDate: Ordering[LocalDate] = _.compareTo(_)
      implicit val orderingLocalDateTime: Ordering[LocalDateTime] = _.compareTo(_)
      implicit val orderingZonedDateTime: Ordering[ZonedDateTime] = _.compareTo(_)


        def fromTemporalToEvery[T <: Temporal](t:T)(i:Int, chronoUnit:ChronoUnit)(implicit iToT: Instant => T, tToI:T => Instant):LazyList[T] =
            LazyList.iterate(tToI(t))(ldt => ldt.plus(i, chronoUnit)).map(iToT)


      def range[T <: Temporal](from: T, to: T, i:Int, chronoUnit:ChronoUnit)(implicit ord:Ordering[T], iToT: Instant => T, tToI:T => Instant): LazyList[T] = {
        fromTemporalToEvery(from)(i, chronoUnit).takeWhile(d => ord.lteq(d, to))
      }

      case class DateRange[T <: Temporal](from:T, to:T) {
        def every(i:Int, chronoUnit:ChronoUnit)(implicit ord:Ordering[T]):LazyList[T] =
            LazyList.iterate(from)(t => t.plus(i, chronoUnit).asInstanceOf[T]).takeWhile(ord.lteq(_, to))
      }
      def range2[T <: Temporal](from: T, to: T, i:Int, chronoUnit:ChronoUnit)(implicit ord:Ordering[T]):LazyList[T] = {
        LazyList.iterate(from)(t => t.plus(i, chronoUnit).asInstanceOf[T]).takeWhile(ord.lteq(_, to))
      }

      val result = range[LocalDate](LocalDate.of(2010, 1, 10), LocalDate.of(2011, 3, 13), 3, DAYS)
      val result2 = range2[LocalDate](LocalDate.of(2010, 1, 10), LocalDate.of(2011, 3, 13), 3, DAYS)
        val result3 = range2[LocalDateTime](LocalDateTime.of(2010, 1, 10, 12, 0, 1), LocalDateTime.of(2011, 3, 13, 13, 4, 50), 3, DAYS)
        val result4 = range2[ZonedDateTime](ZonedDateTime.of(2010, 1, 10, 12, 0, 1, 0, ZoneId.of("America/Los_Angeles")), ZonedDateTime.of(2011, 3, 13, 13, 4, 50, 0, ZoneId.of("America/Los_Angeles")), 3, DAYS)
    }

    it ("can be used cleanly like this") {
        case class DateRange[T <: Temporal](from:T, to:T) {
            def every[S >: T](i:Int, chronoUnit:ChronoUnit)(implicit ord:Ordering[S]):LazyList[T] =
                LazyList.iterate(from)(_.plus(i, chronoUnit).asInstanceOf[T]).takeWhile(ord.lteq(_, to))
        }

        val result2 = DateRange(from = LocalDate.of(2010, 1, 10), to = LocalDate.of(2011, 3, 13)).every(3, DAYS)
        val result3 = DateRange(from = LocalDateTime.of(2010, 1, 10, 12, 0, 1),
            to = LocalDateTime.of(2011, 3, 13, 13, 4, 50))
            .every(3, WEEKS)
        val result4 = DateRange(from = ZonedDateTime.of(2010, 1, 10, 12, 0, 1, 0, ZoneId.of("America/Los_Angeles")), to = ZonedDateTime.of(2011, 3, 13, 13, 4, 50, 0, ZoneId.of("America/Denver"))).every(1, YEARS)

        println(result2.take(10).toList)
        println(result3.take(10).toList)
        println(result4.take(10).toList)
    }
  }
}
