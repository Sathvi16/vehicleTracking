SmartTraffic Monitor — Real-Time Vehicle Tracking System:

        ┌──────────────────────────┐
        │  Vehicle Data Generator   │
        │ (simulates streaming feed)│
        └────────────┬─────────────┘
                     │  continuous JSON
                     ▼
        ┌──────────────────────────┐
        │  Scala Stream Processor   │
        │  (reads, parses, filters) │
        └────────────┬─────────────┘
                     │  alerts
                     ▼
        ┌──────────────────────────┐
        │  HTTP Alert API           │
        │  (mock or httpbin.org)    │
        └──────────────────────────┘

Receives request in below form:

{
  "vehicleId": "KA09AB1234",
  "zone": "ZONE-5",
  "speed": 84.5,
  "timestamp": 1728213001000
}


Real-Time Processing Rules

Your app processes each message immediately as it arrives.

If speed > 80 → overspeeding alert

If zone == "RESTRICTED" → unauthorized entry alert

Otherwise, ignore or log as normal traffic.

This simulates a stream-processing job where each record is acted upon in real time.

return output in below format:

{
  "vehicleId": "KA09AB1234",
  "alertType": "OVERSPEED",
  "message": "Vehicle KA09AB1234 speeding at 84.5 km/h in ZONE-5",
  "timestamp": 1728213001000
}

Error Handling

If the HTTP request fails → retry up to 3 times before skipping.

If JSON parsing fails → log the bad record and continue.

Keep the app running indefinitely (continuous stream).

Here we have created an endpoint by using node js. Below is that file. 

How to start client?
First install the node js server and add this file in local and copy to node js. Here i have installed as my-api. And running there only. 
Run the below commands to start running that server.js file
cd my-api 
node server.js 

If you have done any changes to that file, then to reflect those changes to server in nodejs, u just copy that file using cp command
cp /Users/karpurapusathvika/server.js /Users/karpurapusathvika/my-api/server.js




