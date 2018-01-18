package it.inzynieria.computers

import io.gatling.core.Predef._
import io.gatling.http.Predef.{ http, regex }

class Simulation4 extends Simulation
    with ScenarioWithSession
    with ActionsWithParsing with NextGenComputers.Config {

  setUp(
    simpleClientScenario.inject(rampUsers(1) over (1))
      .protocols(protocolConfig)
  )
}

trait ScenarioWithSession extends Simulation with ActionsWithParsing {
  val simpleClientScenario = scenario("client path")
    .exec(homepage)
    .exec(session => {
      val links = session("paths").as[List[String]] // !
        .filter(_.matches("""\/computers\/[0-9]+""")).take(5)
      session.set("computerLinks", links)
    })
    .foreach("${computerLinks}", "path")( // !
      exec(computerView("${path}"))
    )
}

trait ActionsWithParsing {
  def computerView(path: String) = http(path)
    .get(path)
    .check(regex("Edit computer"))

  def homepage = http("homepage")
    .get("/")
    .check(regex("Play sample application"))
    .check(regex("""href="(.*)"""").findAll.saveAs("paths"))
}
