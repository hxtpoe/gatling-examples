package it.inzynieria.computers

import io.gatling.core.Predef._

import scala.concurrent.duration._

class Simulation1 extends Simulation with Computers.Scenarios with Computers.Actions with Computers.Config {
  setUp(
    simpleClientScenario.inject(atOnceUsers(200)).protocols(protocolConfig).throttle(
      reachRps(5) in (5 seconds),
      holdFor(5 seconds),
      jumpToRps(15),
      holdFor(5 seconds)
    )
  )
}