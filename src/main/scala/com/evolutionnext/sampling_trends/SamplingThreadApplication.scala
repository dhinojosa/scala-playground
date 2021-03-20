package com.evolutionnext.sampling_trends

import java.util.concurrent.Executors

import scala.collection.mutable.ListBuffer
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor}
import scala.util.Random


object SamplingThreadApplication extends App {
  private val numberThreads = args(0).toInt
  private val namePrefix = args(1)
  private val random = new Random()
  private val runnables = ListBuffer[SamplingTrendThread]()

  private val executorService = Executors.newFixedThreadPool(numberThreads)

  private val executionContext = ExecutionContext.fromExecutorService(executorService)

  for (i <- 0 until numberThreads) {
    val runnable: SamplingTrendThread = new SamplingTrendThread(namePrefix, random, 30.00)
    executionContext.submit(runnable)
    runnables += runnable
  }

  Runtime.getRuntime.addShutdownHook(new Thread() {
    override def run(): Unit = {
      runnables.foreach{r => r.shutdown()}
      executionContext.shutdownNow()
    }
  })



}
