
package Main

object Main {
  def main(args: Array[String]): Unit = {

    val jsonString =
      """
        {
          "vehicleId": "KA09AB1234",
          "zone": "RESTRICTED",
          "speed": 85,
          "timestamp": "2025-10-07T10:00:00Z"
        }
      """

    // Step 2: Convert JSON to VehicleData object
    val vehicle = JsonConverter.fromJson(jsonString)

    // Step 3: Process the vehicle data (check rules)
    val processor = new TrafficProcessor()
    processor.process(vehicle)
  }
}
