package it.inzynieria.computers

import io.gatling.core.Predef._
import io.gatling.http.Predef.{http, regex, css}

class Simulation4 extends Simulation
  with ScenarioWithSession
  with ActionsWithParsing with NextGenComputers.Config {

  setUp(
    simpleClientScenario.inject(rampUsers(1) over(1))
      .protocols(protocolConfig))
}

trait ScenarioWithSession extends Simulation with ActionsWithParsing {
    val simpleClientScenario = scenario("client path")
      .exec(homepage)
        .exec(session => {
          println(session("paths").as[String])
          session})
  }

trait ActionsWithParsing {
  def homepage = http("homepage")
    .get("/")
    .check(regex("Play sample application"))
    .check(regex("""href="(.*)"""").findAll.saveAs("paths"))


  def computerView = http("computer view")
    .get("/computers/381")
    .check(regex("Edit computer"))
}
