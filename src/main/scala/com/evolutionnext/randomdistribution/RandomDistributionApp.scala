package com.evolutionnext.randomdistribution

import scala.collection.mutable.{Map => MutableMap}
import scala.language.postfixOps


object RandomDistributionApp extends App {
  def randomFromUntil(from:Int, to:Int) = random.nextInt(to - from) + from
  def randomFromTo(from:Int, to:Int) = random.nextInt(to - from + 1) + from
  val random = new java.util.Random()
  val mutableMap: MutableMap[Int, Int] = MutableMap.empty[Int, Int]
  LazyList.from(1).take(1000).foreach { _ =>
    val i: Int = randomFromTo(5, 10)
    val n: Int = mutableMap.getOrElse(i, 0) + 1
    mutableMap += (i -> n)
  }
  println(mutableMap.view.mapValues(mutableMap.values.max-))
}
