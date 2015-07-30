package me.eax.examples.statsd.client

import com.timgroup.statsd.NonBlockingStatsDClient

trait MetricsClient {
  def recordTime(name: String, timeMs: Long): Unit
  def recordValue(name: String, value: Long): Unit
  def incrementCounter(name: String, delta: Long = 1L): Unit
}

class MetricsClientImpl extends MetricsClient {
  // TODO: read from config!
  private val prefix = "me.eax"
  private val host = "10.110.0.10"
  private val port = 8125

  private val client = new NonBlockingStatsDClient(prefix, host, port)

  def recordTime(name: String, timeMs: Long): Unit = {
    client.recordExecutionTime(name, timeMs)
  }

  def recordValue(name: String, value: Long): Unit = {
    client.recordGaugeValue(name, value)
  }

  def incrementCounter(name: String, delta: Long = 1L): Unit = {
    client.count(name, delta)
  }
}
