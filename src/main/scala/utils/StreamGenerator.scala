package utils
import play.api.libs.json._

// this is the source of data pipeline. it will read and generate the events continuously in real time basis.

class StreamGenerator(val ip:String) {
  // reads string data which is in json format and parse to vehicle  object.
  val parsed = Json.parse(ip)  // parsed the json string. now convert to vehicle object
  //{
  //  "vehicleId": "KA09AB1234",
  //  "zone": "ZONE-5",
  //  "speed": 84.5,
  //  "timestamp": 1728213001000
  //}

  val vehicleid = parsed("vehicleId").str
  val zone:String = parsed("zone").str
  val speed:Double = parsed("speed").num.toDouble
  val timestamp = parsed("timestamp").num.toLong
  val obj_vehicle = new VehicleData(vehicleid, zone,speed, timestamp)
  println("Vehicle object gas created successfully. ")
//  obj_vehicle.showDetails()

}

