package it.inzynieria.computers

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Simulation0refactored extends Simulation with Computers.Scenarios with Computers.Actions with Computers.Config {
  setUp(
    simpleClientScenario.inject(atOnceUsers(50)).protocols(protocolConfig)
  )
}

object Computers {
  trait Actions {
    def homepage = http("homepage")
      .get("/")
      .check(regex("Play sample application"))

    def computerView = http("computer view")
      .get("/computers/381")
      .check(regex("Edit computer"))
  }

  trait Config {
    val protocolConfig = http.baseURL("http://computer-database.gatling.io")
  }

  trait Scenarios extends Simulation with Actions {
    val simpleClientScenario = scenario("client path").exec(homepage).repeat(5) { exec(computerView) }
  }
}