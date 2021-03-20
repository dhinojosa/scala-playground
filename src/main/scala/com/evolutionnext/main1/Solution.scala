package com.evolutionnext.main1

object Solution {
  def isSorted(a: Array[Int]): Boolean = {
    a.zip(a.tail).forall { t => t._2 >= t._1 }
  }

  def solution(a: Array[Int]): Boolean = {
      val indices = a.indices
      indices.flatMap(x => indices.map(y => {
        val temp = a(y); a.updated(y, a(x)).updated(x, temp)
      })).map(c => isSorted(c)).exists(b => b)
  }
}

//Lessons
//Use view or parallel
//If it only needs one answer, deliver only one
//Go back and re-read to make sure you didn't miss something

object Runner {
  def main(args: Array[String]): Unit = {
    assert(Solution.solution(Array()) == false)
    assert(Solution.solution(Array(1)) == true)
    assert(Solution.solution(Array(1, 2)) == true)
    assert(Solution.solution(Array(2, 1)) == true)
    assert(Solution.solution(Array(1, 5, 3, 3, 7)) == true)
    assert(Solution.solution(Array(25, 10, 33, 19, 20, 2, 1)) == false)
    assert(Solution.solution(Array(10, 9, 8, 4, 5, 3, 2, 1)) == false)
    assert(Solution.solution(Array(1, 3, 5)) == true)
  }
}
