package com.evolutionnext.numbering
import org.scalatest._
import org.scalatest.funspec._
import org.scalatest.matchers._

class NumberingSpec extends AnyFunSpec with should.Matchers {
  describe("simple parsing with numbers less than 999") {
    it("should parse 10") {
      Numbering.parseGroup("10") should be(Some("Ten"))
    }
    it("should parse 16") {
      Numbering.parseGroup("16") should be(Some("Sixteen"))
    }
    it("should parse 11") {
      Numbering.parseGroup("11") should be(Some("Eleven"))
    }
    it("should parse 20") {
      Numbering.parseGroup("20") should be(Some("Twenty"))
    }
    it("should parse 29") {
      Numbering.parseGroup("29") should be(Some("Twenty Nine"))
    }
    it("should parse 401") {
      Numbering.parseGroup("401") should be(Some("Four Hundred One"))
    }
    it("should parse 888") {
      Numbering.parseGroup("888") should be(
        Some("Eight Hundred Eighty Eight")
      )
    }
    it("should parse 999") {
      Numbering.parseGroup("999") should be(
        Some("Nine Hundred Ninety Nine")
      )
    }
    it("should describe a single digit without 2 prefixes") {
      Numbering.parseGroup("001") should be(Some("One"))
    }
    it("should describe a double digit without 1 prefixes") {
      Numbering.parseGroup("010") should be(Some("Ten"))
    }
    it("should describe a 12 without 1 prefixes") {
      Numbering.parseGroup("012") should be(Some("Twelve"))
    }
    it("should describe a triple digit without 0 prefixes") {
      Numbering.parseGroup("999") should be(
        Some("Nine Hundred Ninety Nine")
      )
    }
  }

  describe("translate entire number") {
    it("should translate 3012 into Some(Three Thousand Twelve)") {
      Numbering.translate("3012") should be("Three Thousand Twelve")
    }

    it(
      "should translate 133002 into Some(One Hundred Thirty Three Thousand Two)"
    ) {
      Numbering.translate("133002") should be(
        "One Hundred Thirty Three Thousand Two"
      )
    }

    it(
      "should translate 120,000,000 into Some(One Hundred Twenty Million)"
    ) {
      Numbering.translate("120000000") should be(
        "One Hundred Twenty Million"
      )
    }
  }
}
