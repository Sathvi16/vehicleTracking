package services

import java.sql.Timestamp
// this class will accept the vehicle data and process data based on traffic rules and send the report.


class TrafficProcessor  {
  def process(vehicle:VehicleData): Unit = {
    if(vehicle.speed>80){
      print("Overspeed")
//     hit the overspeed url.
      val message = s"Vehicle ${vehicle.vehicleId} speeding at ${vehicle.speed} km/h in ${vehicle.zone}"
      val alertType = "OVERSPEED"
    }
    else if(vehicle.zone=="RESTRICTED"){
      println("unauthorized entry")
    }
    else{
      println("Normal traffic")
    }
  }

}

