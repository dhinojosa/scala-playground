package com.evolutionnext.sampling_trends

import java.util.concurrent.atomic.AtomicBoolean

import scala.util.Random

class SamplingTrendThread(name: String, rnd: Random, initialValue: Double)
  extends Runnable {

  private val atomicBoolean = new AtomicBoolean()

  def currentThreadName: String = Thread.currentThread().getName

  def shutdown(): Unit = {
    atomicBoolean.set(true)
  }

  override def run(): Unit = {
    createStream.map(t => (atomicBoolean.get(), t))
      .takeWhile(!_._1)
      .map(_._2)
      .foreach { t =>
        println(t)
        Thread.sleep(rnd.nextInt(3000))
      }
  }

  private def createStream: Stream[(String, String, Double, Double)] = {

    val positiveGaussian = Stream.continually(rnd.nextGaussian)
      .map(n => n / 2.0) //lessen variance
      .filter(x => x > 0.0)

    val negativeGaussian = Stream.continually(rnd.nextGaussian)
      .map(n => n / 2.0) //lessen variance
      .filter(x => x < 0.0)

    val trend = Stream.continually(rnd.nextInt(5))

    val qualityTrend = Stream.continually(rnd.nextBoolean)

    val qualityCountTrend: Stream[(Boolean, Int)] = qualityTrend.zip(trend)

    val temps: Stream[Double] = qualityCountTrend
      .flatMap { case (b, n) =>
        val gaussian = if (b) positiveGaussian else negativeGaussian
        gaussian.take(n)
      }

    val stream: Stream[(Double, Double)] =
      temps.scan(initialValue)((total, next) => total + next).zip(temps)

    stream.map(t => (name, currentThreadName, t._1, t._2))
  }
}
