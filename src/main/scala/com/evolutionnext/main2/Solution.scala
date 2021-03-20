package com.evolutionnext.main2

import scala.collection.JavaConverters._

class Tree(var x: Int, var l: Tree, var r: Tree)

object Solution {

  def seek(t: Tree, left: Boolean, acc: Int): Int = {
    if (t.l == null && t.r == null) acc
    else {
      val leftBranch: Int = if (t.l != null) { //has left
        if (left) seek(t.l, left = true, acc)
        else seek(t.l, left = true, acc + 1) //if we were right switch
      } else 0
      val rightBranch: Int = if (t.r != null) {
        if (left) seek(t.r, left = false, acc + 1)
        else seek(t.r, left = false, acc)
      } else 0
      leftBranch.max(rightBranch)
    }
  }

  def solution(t: Tree): Int = {
    if (t == null) 0
    else {
      val leftBranch = if (t.l != null) seek(t.l, left = true, 0) else 0
      val rightBranch = if (t.r != null) seek(t.r, left = false, 0) else 0
      leftBranch max rightBranch
    }
  }
}

//Lessons:
//Use view or parralel
//If it only needs one answer, deliver only one
//Go back and re-read to make sure you didn't miss something

object Runner {
  def main(args: Array[String]): Unit = {
    assert(Solution.solution(null) == 0)
    assert(Solution.solution(new Tree(5, null, null)) == 0)
    assert(Solution.solution(new Tree(5, new Tree(3, null, null), null)) == 0)
    assert(Solution.solution(new Tree(5, new Tree(3, null, new Tree(10, null, null)), null)) == 1)
  }
}