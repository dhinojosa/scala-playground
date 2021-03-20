package com.evolutionnext.wordformat

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should

import scala.annotation.tailrec
import scala.util.Try

class WordFormatSpec extends AnyFunSpec with should.Matchers {

  def getIndexLessThatEqualToLimit(s: String, width: Int): Int = {
    Try(s.charAt(width))
      .map(at =>
        if (at.isSpaceChar) width
        else {
          val temp = s.substring(0, width).lastIndexOf(" ")
          if (temp == -1) s.length
          else temp
        }
      )
      .fold(_ => s.length, identity)
  }

  def format(s: String, limit: Int): List[String] = {
    @tailrec
    def recur(snippet: String, acc: List[String]): List[String] = {
      printf("snippet: %s\n", snippet)
      if (snippet.isEmpty) acc
      else {
        val left = getLeft(limit, snippet)
        val right = getRight(limit, snippet)
        printf("left: %s, right: %s\n", left, right)
        recur(left, acc :+ right)
      }
    }
    recur(s, Nil)
  }

  def foo(s: String, limit: Int): List[String] = {
    val tuple: (Int, List[String]) = s
      .split(" ")
      .toList
      .foldLeft(0 -> List.empty[String]) { (t, w) =>
        val size = t._1
        val xs = t._2
        if (size + w.length <= limit) (size + w.length) -> (xs :+ w)
        else (size, xs)
      }
    tuple._2
  }

  //curr:  The   bar      closes   at        midnight  due  to  the    virus
  //next: The bar  closes   at       midnight  due       to   the virus

  private def getRight(limit: Int, s: String) = {
    s.substring(0, getIndexLessThatEqualToLimit(s, limit)).trim
  }

  private def getLeft(limit: Int, s: String) = {
    s.substring(getIndexLessThatEqualToLimit(s, limit)).trim
  }

  describe("""Getting the various formatted strings""") {
    it("should do anything with an empty string") {
      getIndexLessThatEqualToLimit("", 0) should be(0)
    }
    it("should return just the word if the length is far fewer than the limit") {
      getIndexLessThatEqualToLimit("cloud", 80) should be(5)
    }
    it("should return just the word if it slightly less than limit") {
      getIndexLessThatEqualToLimit("clouds now", 10) should be(10)
    }
    it("should not exceed the limit") {
      getIndexLessThatEqualToLimit("clouds eccentric", 10) should be(6)
    }
    it("should not exceed the limit if at 10") {
      getIndexLessThatEqualToLimit("the rye is good but sweet", 10) should be(10)
    }
    it("should work with a space after") {
      getIndexLessThatEqualToLimit("boarded the plane drunk and kept pulling on each other's handkerchiefs",
                                   10
      ) should be(7)
    }
  }
  describe("""formatting""") {
    it("""should format an empty string as a List""") {
      format("", 10) should be(Nil)
    }
    it("""should format a single string that is less than the limit as one string""") {
      format("The rye is good", 80) should be(List("The rye is good"))
    }
    it("""should format a couple of more lines""") {
      format("The rye is good but sweet", 10) should be(List("The rye is", "good but", "sweet"))
    }
    it("""should format a couple of more lines and still hold""") {
      format("Ten clowns boarded the plane drunk and kept pulling on each other's handkerchiefs", 30) should be(
        List("Ten clowns boarded the plane", "drunk and kept pulling on each", "other's handkerchiefs")
      )
    }
    it(
      """should format a couple of more lines and still hold but with a word that exceeds the limit and has no spaces"""
    ) {
      format("Ten clowns boarded the plane drunk and kept pulling on each other's handkerchiefs", 10) should be(
          List("Ten clowns", "boarded", "the plane", "drunk and", "kept", "pulling on", "each", "other's", "handkerchiefs")
      )
    }
  }
}
