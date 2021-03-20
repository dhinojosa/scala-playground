package com.evolutionnext.problem2

object Solution {
  def solution(a: Array[Int]): Int =
     a.zipWithIndex
    .view
    .groupBy { case (value, idx) => value }
    .toList
    .minBy(_._2.size)._1
}

object Runner extends App {
  assert(Solution.solution(Array[Int](1)) == 1)
  assert(Solution.solution(Array[Int](1, 2, 1)) == 2)
  assert(Solution.solution(Array[Int](1, 2, 1, 2, 2, 2, 3, 1, 1)) == 3)
  assert(Solution.solution(Array[Int](1, 2, 1, 2, 1, 2, 1, 2, 3, 3, 3, 9, 3, 2, 1, 1, 1, 2, 3, 3, 1)) == 9)
}
