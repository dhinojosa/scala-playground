package com.evolutionnext.problem1

import scala.language.postfixOps

object Solution {
  def solution(n: Int): Int = {
    val binaryString = n.toBinaryString.dropWhile('0'==).reverse.dropWhile('0'==).reverse
    binaryString.zip(binaryString.tail).foldLeft(0, 0) { case ((record, total), (prev, curr)) =>
      println(s"prev: $prev, curr: $curr, record: $record, total: $total")
      if (prev == curr && curr == '0')
        if (total == record) (record + 1, total + 1) else (record, total + 1)
      else (record, total)
    }._2 + 1
  }
}

object Runner {
  def main(args: Array[String]): Unit = {
    println(302.toBinaryString)
    println(Solution.solution(302))
  }
}

