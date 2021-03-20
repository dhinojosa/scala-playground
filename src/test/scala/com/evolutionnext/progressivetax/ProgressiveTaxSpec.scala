package com.evolutionnext.progressivetax

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should

class ProgressiveTaxSpec extends AnyFunSpec with should.Matchers {
  describe("Look up a tax table to determine the tax") {
    it("can be done purely through functions") {
      val map = Map(
        10000 -> .10,
        100000 -> .30,
        200000 -> .35,
        500000 -> .50,
        1000000 -> .60,
        10000000 -> .70
      )

      val f: Int => Double = (x: Int) =>
        map.toList.sortBy(t => t._1).takeWhile(i => x >= i._1).last._2

      val applyTax = (strategy: Int => Double) =>
        (amount: Int) => strategy.apply(amount) * amount

      val applyTaxUsingTable = applyTax(f)

      applyTaxUsingTable(100000) should be(30000.0)
      applyTaxUsingTable(100010) should be(30003.0)
    }

    it("should be possible to replace the strategy easily using functions") {
      val strategy: Int => Double = x => x * .30

      val applyTax = (strategy: Int => Double) =>
        (amount: Int) => strategy(amount)

      val applyTaxUsingTable = applyTax(strategy)

      applyTaxUsingTable(100000) should be(30000.0)
      applyTaxUsingTable(100010) should be(30003.0)
    }
  }
}
