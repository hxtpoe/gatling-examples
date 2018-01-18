package it.inzynieria.computers

import io.gatling.core.Predef._

import scala.concurrent.duration._

class Simulation3 extends Simulation
    with NextGenComputers.Scenarios
    with NextGenComputers.Actions with NextGenComputers.Config {

  setUp(
    simpleClientScenario.inject(rampUsers(250) over (20 seconds))
      .protocols(protocolConfig)
  )
}

