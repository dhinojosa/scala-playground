package com.evolutionnext.diamond

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should

class DiamondSpec extends AnyFunSpec with should.Matchers {
  info("WARNING: IntelliJ doesn't respect spacing")
  describe("Diamond Printing") {
    it("should handle the letter a and just return a") {
      val result = Diamond.mkDiamond('a')
      result should be("a")
    }
    it("should handle the letter b and just return a small diamond") {
      val expected =
        """ a 
          |b b
          | a """.stripMargin
      val result = Diamond.mkDiamond('b')
      result should be(expected)
    }
    it("should handle the letter c and just return a small diamond with c") {
      val expected =
        """  a  
          | b b 
          |c   c
          | b b 
          |  a  """.stripMargin
      val result = Diamond.mkDiamond('c')
      result should be(expected)
    }
  }
}
