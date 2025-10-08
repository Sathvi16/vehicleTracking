package utils
import models.VehicleData
import play.api.libs.json._
import java.time.{Instant, ZoneId, ZonedDateTime}

// this is the source of data pipeline. it will read and generate the events continuously in real time basis.

class StreamGenerator(val ip:String) {
  // reads string data which is in json format and parse to vehicle  object.
  def processing():VehicleData= {

    val parsed = Json.parse(ip) // parsed the json string. now convert to vehicle object
    //{
    //  "vehicleId": "KA09AB1234",
    //  "zone": "ZONE-5",
    //  "speed": 84.5,
    //  "timestamp": 1728213001000
    //}

    val vehicleId = (parsed \ "vehicleId").as[String]
    val zone = (parsed \ "zone").as[String]
    val speed = (parsed \ "speed").as[Double]

    // Handle timestamp as either number or ISO string
    val timestampJsValue: JsValue = (parsed \ "timestamp").get

    val timestamp: Long = timestampJsValue match {
      case JsNumber(num) =>
        num.toLong
      case JsString(str) =>
        Instant.parse(str).toEpochMilli
      case _ =>
        throw new IllegalArgumentException("Invalid timestamp format")
    }

    val obj_vehicle = new VehicleData(vehicleId, zone, speed, timestamp)
    println("Vehicle object gas created successfully. ")
    obj_vehicle
    //  obj_vehicle.showDetails()
  }
}

