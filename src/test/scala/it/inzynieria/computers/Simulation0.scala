package it.inzynieria.computers

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Simulation0 extends Simulation {
  val client = scenario("client path")
    .exec(homepage).exec(computerView).exec(computerView)
    .exec(computerView).exec(computerView).exec(computerView)

  def homepage = http("homepage")
    .get("http://computer-database.gatling.io/")
    .check(status.is(200))
    .check(regex("Play sample application"))

  def computerView = http("computer view")
    .get("http://computer-database.gatling.io/computers/381")
    .check(status.is(200))
    .check(regex("Edit computer"))

  // SIMULATION configuration
  setUp(
    client.inject(atOnceUsers(50))
  )
}
