import io.gatling.core.Predef._
import io.gatling.core.session
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._
import scala.language.postfixOps
import scalaj.http._


import scala.util.Random


class ClickhouseRequest extends Simulation {

  val url = "http://localhost:8123"

   val feeder = Iterator.continually(Map("event_id" -> java.util.UUID.randomUUID().toString))

  before {
    Http(url + "/").postData("CREATE TABLE IF NOT EXISTS events_mem (event_date Date, event_time DateTime, event_id UUID, event_data String) ENGINE = Memory").asString
  }

  setUp(scenario("Scenario name").repeat(200) {
    feed(feeder).exec(http(s"Insert request")
      .post("/").body(StringBody("INSERT INTO events_mem(event_date, event_time, event_id, event_data) values ('2019-01-01', '2019-01-01 00:00:00', '${event_id}', 'Some text')"))
    ).exec(http(s"Select request")
      .post("/").body(StringBody("SELECT * FROM events_mem WHERE `event_id` = '${event_id}'"))
    ) //session: Session =>
  }.inject(atOnceUsers(20)).protocols(http.baseUrl(url)))

  //.throttle(reachRps(200) in (30 seconds), holdFor(90 seconds))

  after {
    Http(url+"/").postData("DROP TABLE events_mem").asString
  }
}

