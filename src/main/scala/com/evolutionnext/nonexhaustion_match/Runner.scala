package com.evolutionnext.nonexhaustion_match

object Runner extends App {
  final class X {
    def foo(b: Boolean): Int =
      if (b) 10 else 11
  }

  val x = new X()
  println(x.foo(true))
}
