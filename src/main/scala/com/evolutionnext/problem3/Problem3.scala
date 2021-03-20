package com.evolutionnext.problem3

object Solution {
  def solution(a: Array[Int]): Int = {
    if(a.isEmpty) 0
    else {
       val m = a.length
       val remainder: Int = a.foldLeft(0)((total, next) => {
         val result = if (next != a.length + 1) m - next else total
         println(s"next: $next, total: $total, a.length: ${a.length}, result: $result")
         result
       })
       a.length - 1 - remainder.abs
    }
  }
}

object Runner {
  def main(args: Array[String]): Unit = {
    assert(Solution.solution(Array()) == 0, () => "0")
    assert(Solution.solution(Array(1,3)) == 2, () => "1,3")
  }
}
// [1,2] = 3 (2) , 3

// [1,3] = 4 (2) , 2
// [3,2] = 5 (2) , 1




