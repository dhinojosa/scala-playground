package com.evolutionnext


object CommaProcessing extends App {

  def process(first:String, args: String*) {
    println("first:" + first)
    println("As a whole: " + args)
    args.foreach(x => println("String:" + x))
  }

  val array1 = Array("Foo", "Bar", "Baz")
  val array2 = Array("Sam", "Ann", "Fran")

  process("Nice", array1 ++ array2: _*)
}
