package com.evolutionnext.implicitclass

object MyPredef {
  implicit class IntWrapper(x:Int) {
    def isOdd: Boolean = x % 2 != 0
  }
}

object ImplicitClass extends App {
  import MyPredef._
  10.isOdd
}
