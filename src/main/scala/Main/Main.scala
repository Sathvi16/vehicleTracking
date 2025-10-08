package Main
import utils.StreamGenerator
import services.TrafficProcessor
import scala.util.Random

object Main {
  def randomVehicleId(): String = {
    val states = Seq("KA", "MH", "DL", "TN", "GJ", "UP", "RJ", "PB", "HR", "WB")
    val letters = ('A' to 'Z')
    val rand = new scala.util.Random()
    val stateCode = states(rand.nextInt(states.length))
    val district = f"${rand.nextInt(99)}%02d" // two digits
    val series = s"${letters(rand.nextInt(26))}${letters(rand.nextInt(26))}"
    val number = f"${rand.nextInt(9999)}%04d"
    s"$stateCode$district$series$number"
  }


  def main(args: Array[String]): Unit = {
    val vehicle_id = randomVehicleId()
    val zones = List("NORMAL", "RESTRICTED","DANGER")
    val rand = new Random()
    while (true) {
      val jsonString =
        s"""
        {
          "vehicleId": "${randomVehicleId()}",
          "zone": "${zones(rand.nextInt(zones.length))}",
          "speed": ${rand.nextInt(60)},
          "timestamp": "${java.time.Instant.now}"
        }
      """
      // Step 2: Convert JSON to VehicleData object
      val obj1 = new StreamGenerator(jsonString) // creates streamprocessor object
      // Step 3: Process the vehicle data (check rules)
      val vehicle = obj1.processing() // it will convert the above json string to vehicledata object and send to processing.
      val processor = new TrafficProcessor()
      processor.process(vehicle) // this will process the data and send to http endpoints
    }


  }
}
