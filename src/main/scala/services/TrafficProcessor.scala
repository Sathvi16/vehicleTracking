package services

import java.sql.Timestamp
import models.VehicleData
import utils.HttpClient
// this class will accept the vehicle data and process data based on traffic rules and send the report.

//Processing vehicle KA09AB1234
//Unauthorized entry detected in RESTRICTED zone
//  Sending alert to http://localhost:3000/api/traffic
//  Alert sent successfully: traffic alert endpoint!
//Traffic endpoint was called --> in node js console
//

class TrafficProcessor  {
  def process(vehicle:VehicleData): Unit = {
    val client = new HttpClient()
    var alertType = " "
    var message = " "
    var endpoint = " "
    println("Processing vehicle KA09AB1234")
    if(vehicle.speed>80){
      println("Overspeed")
      endpoint = "http://localhost:3000/api/overspeed/"
      println(s"Overspeed alert detected in ${vehicle.zone} zone")
      println(s"sending alert to ${endpoint}")
      alertType = "OVERSPEED"
      message = s"Vehicle ${vehicle.vehicleId} speeding at ${vehicle.speed} km/h in ${vehicle.zone} zone"
      client.sendAlert(vehicle,alertType,message,endpoint)
      println("Alert Sent successfully")
    }
    if(vehicle.zone=="RESTRICTED"){
      alertType="Unauthorized entry"
      endpoint="http://localhost:3000/api/unauthorized/"
      println(s"Unauthorized entry detected in ${vehicle.zone} zone")
      println(s"sending alert to ${endpoint}")
      message = s"Vehicle ${vehicle.vehicleId} speeding at ${vehicle.speed} km/h in ${vehicle.zone} zone"
      client.sendAlert(vehicle,alertType,message,endpoint)
      println("Alert Sent successfully")
    }
    else{
      alertType="Normal traffic"
      println("Normal Traffic")
    }


  }

}

