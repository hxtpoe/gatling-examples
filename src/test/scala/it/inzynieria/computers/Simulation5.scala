package it.inzynieria.computers

import io.gatling.core.Predef._
import io.gatling.http.Predef.{ http, regex }

class Simulation5 extends Simulation
    with ScenarioWithResources
    with ActionsWithResources with NextGenComputers.Config {

  setUp(
    simpleClientScenario.inject(rampUsers(1) over (1))
      .protocols(protocolConfig)
  )
}

trait ScenarioWithResources extends Simulation with ActionsWithResources {
  val simpleClientScenario = scenario("client path")
    .exec(homepage.resources(
      ajaxCall,
      ajaxCall,
      ajaxCall,
      ajaxCall,
      ajaxCall
    ))
}

trait ActionsWithResources {
  def ajaxCall = http("computers")
    .get("/computers/501")
    .check(regex("Edit computer"))

  def homepage = http("homepage")
    .get("/")
    .check(regex("Play sample application"))
    .check(regex("""href="(.*)"""").findAll.saveAs("paths"))
}
