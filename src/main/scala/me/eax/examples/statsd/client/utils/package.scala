package me.eax.examples.statsd.client

import scala.compat._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

package object utils {
  private val client = new MetricsClientImpl

  def recordTime[T](metricName: String)(f: => T): T = {
    val startTimeMs = Platform.currentTime
    val result = f
    val endTimeMs = Platform.currentTime
    client.synchronized {
      client.recordTime(metricName, endTimeMs - startTimeMs)
    }
    result
  }

  def recordTimeF[T](metricName: String)(f: => Future[T]): Future[T] = {
    val startTimeMs = Platform.currentTime
    val fResult = f
    // TODO: check if future is completed successfully
    fResult.onComplete { case _ =>
      val endTimeMs = Platform.currentTime
      client.synchronized {
        client.recordTime(metricName, endTimeMs - startTimeMs)
      }
    }
    fResult
  }
}
