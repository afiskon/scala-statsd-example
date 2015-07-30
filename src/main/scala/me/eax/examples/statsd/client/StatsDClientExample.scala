package me.eax.examples.statsd.client

import scala.util._
import scala.concurrent._
import me.eax.examples.statsd.client.utils._
import scala.concurrent.ExecutionContext.Implicits.global

object StatsDClientExample extends App {
  val client = new MetricsClientImpl

  for(i <- 1 to 500) {
    val inc = (1 + Random.nextInt(5)).toLong
    val time = (1 + Random.nextInt(100)).toLong
    val value = (1 + Random.nextInt(1000)).toLong
    client.incrementCounter("test.counter", inc)
    client.recordTime("test.time", time)
    client.recordValue("test.value", value)
    recordTime("thread.sleep.future") { Future { Thread.sleep(100) } }
    recordTime("thread.sleep") { Thread.sleep(100) }
  }
}
