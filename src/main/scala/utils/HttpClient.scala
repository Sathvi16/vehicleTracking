package utils

import javax.annotation.processing.Messager
import play.api.libs.json._
import java.net.{HttpURLConnection, URL}
import java.io.{OutputStreamWriter, BufferedReader, InputStreamReader}
import models.VehicleData

class HttpClient {
  def sendAlert(vehicle:VehicleData,alertType:String,message:String,endpoint:String): Unit = {
    // prepare a json with this information and pass to endpoint as a json content.

    val json: JsValue = Json.obj(
      "vehicleId" -> vehicle.vehicleId,
      "zone" -> vehicle.zone,
      "speed" -> vehicle.speed,
      "timestamp" -> vehicle.timestamp,
      "alertType" -> alertType,
      "message" -> message
    )

    val jsonString: String = Json.stringify(json)
    println(s"Json string is : ${jsonString}")

    val url = new URL(endpoint)
    val conn = url.openConnection().asInstanceOf[HttpURLConnection]
    try {
      conn.setDoOutput(true)
      conn.setRequestMethod("POST")
      conn.setRequestProperty("Content-Type", "application/json")

      // Write JSON data to output stream
      val writer = new OutputStreamWriter(conn.getOutputStream)
      writer.write(jsonString)
      writer.flush()
      writer.close()

      val responseCode = conn.getResponseCode
      println(s"Response Code: $responseCode")

      val inputStream = if (responseCode >= 200 && responseCode < 400) {
        conn.getInputStream
      } else {
        conn.getErrorStream
      }

      val reader = new BufferedReader(new InputStreamReader(inputStream))
      val response = new StringBuilder
      var line = reader.readLine()
      while (line != null) {
        response.append(line)
        line = reader.readLine()
      }
      reader.close()

      println(s"Response Body: ${response.toString}")

    }
    finally {
      conn.disconnect()
    }
  }
}
