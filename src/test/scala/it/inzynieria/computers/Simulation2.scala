package it.inzynieria.computers

import io.gatling.core.Predef._
import io.gatling.http.Predef.{ http, regex }

import scala.concurrent.duration._

class Simulation2 extends Simulation with NextGenComputers.Scenarios with NextGenComputers.Actions with NextGenComputers.Config {
  setUp(
    simpleClientScenario.inject(atOnceUsers(50)).protocols(protocolConfig)
  )
}

object NextGenComputers {
  trait Scenarios extends Simulation with Actions {
    val simpleClientScenario = scenario("client path")
      .exec(homepage).pause(4 seconds, 5 seconds)
      .repeat(5) { exec(computerView).pause(4 seconds, 12 seconds) }
  }

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
}