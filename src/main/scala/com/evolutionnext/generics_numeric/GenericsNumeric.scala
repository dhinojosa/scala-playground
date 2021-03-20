package com.evolutionnext.generics_numeric

class GenericsNumeric {
  def foo[A : Ordering](a:A, b:A) :Boolean = {
    val converter = implicitly[Ordering[A]]
    converter.gt(a,b)
  }
}

object GenericsNumericRunner {
  def main(args: Array[String]): Unit = {
    val genericsNumeric = new GenericsNumeric
    println(genericsNumeric.foo(40, 30))
  }
}
