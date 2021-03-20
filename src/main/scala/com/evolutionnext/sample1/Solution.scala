package com.evolutionnext.sample1

import scala.collection.JavaConverters._

object Solution {
  def solution(a: Array[Int]): Int = {
      a.indices.view.find(i => {
        val t = a.splitAt(i)
        val left = t._1.foldLeft(BigInt(0))(_+_)
        val right = t._2.tail.foldLeft(BigInt(0))(_+_)
        println(s"idx: $i, left: $left, right: $right")
        left == right
      }).getOrElse(-1)
  }
}

/*
This is a demo task.

A zero-indexed array A consisting of N integers is given. An equilibrium index of this array is any integer P such that 0 ≤ P < N and the sum of elements of lower indices is equal to the sum of elements of higher indices, i.e.
A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N−1.

For example, consider the following array A consisting of N = 8 elements:

  A[0] = -1
  A[1] =  3
  A[2] = -4
  A[3] =  5
  A[4] =  1
  A[5] = -6
  A[6] =  2
  A[7] =  1
P = 1 is an equilibrium index of this array, because:

A[0] = −1 = A[2] + A[3] + A[4] + A[5] + A[6] + A[7]
P = 3 is an equilibrium index of this array, because:

A[0] + A[1] + A[2] = −2 = A[4] + A[5] + A[6] + A[7]
P = 7 is also an equilibrium index, because:

A[0] + A[1] + A[2] + A[3] + A[4] + A[5] + A[6] = 0
and there are no elements with indices greater than 7.

P = 8 is not an equilibrium index, because it does not fulfill the condition 0 ≤ P < N.

Write a function:

object Solution { def solution(a: Array[Int]): Int }

that, given a zero-indexed array A consisting of N integers, returns any of its equilibrium indices. The function should return −1 if no equilibrium index exists.

For example, given array A shown above, the function may return 1, 3 or 7, as explained above.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

Copyright 2009–2017 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.

 */

object Runner {
  def main(args: Array[String]): Unit = {
    assert(Solution.solution(Array()) == -1)
    assert(Solution.solution(Array(0)) == 0)
    assert(Solution.solution(Array(1)) == 0)
    assert(Solution.solution(Array(0,0)) == 0)
    assert(Solution.solution(Array(0, 2, -1)) == -1)
    assert(Solution.solution(Array(0, -2, 2)) == 0)
    assert(Solution.solution(Array(1, 2)) == -1)
    assert(Solution.solution(Array(1, 2, 1)) == 1)
    assert(Solution.solution(Array(-1, 1)) == -1)
    assert(Solution.solution(Array(-50, 14, -20, -22, -8)) == 1)
    assert(Solution.solution(Array(-1, 3, -4, 5, 1, -6, 2, 1)) == 1)
    assert(Solution.solution(Array(-4000, -3, 2, 3, 4000)) == -1)
    assert(Solution.solution(Array(Integer.MIN_VALUE, Integer.MAX_VALUE, 2, Integer.MIN_VALUE, Integer.MAX_VALUE)) == 2)

    //  println(Solution.solution((0 until Integer.MAX_VALUE - 1024).toArray))
    //assert(Solution.solution((Integer.MIN_VALUE to Integer.MAX_VALUE).toArray) == 1)

    //Lessons
    //use view
    //or parralel
    //if it only needs one answer, deliver only one

  }

}
