package models

import java.sql.Timestamp
import play.api.libs.json.{Reads,Json}


// this file will create vehiclesdata object and in real time streaming, we will get continuous data from this object.
//Sample data is shown below.
//{
//  "vehicleId": "KA09AB1234",
//  "zone": "ZONE-5",
//  "speed": 84.5,
//  "timestamp": 1728213001000
//}

//class VehicleData(val vehicleId:String, val zone:String, val speed:Double, val timestamp: Long) {
//    def showDetails():Unit={
//      println(s"VehicleId is ${vehicleId}")
//      println(s"zone is ${zone}")
//      println((s"speed is ${speed}"))
//      println(s"Timestamp value is ${timestamp}")
//    }
//}

case class VehicleData(val vehicleId:String, val zone:String, val speed:Double, val timestamp: Long)
//implicit val reads: Reads[VehicleData] = Json.reads[VehicleData]
