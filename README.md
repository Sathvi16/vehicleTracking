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

Server.js file:

const express = require('express');
const app = express();
const port = 3000;

app.use(express.json()); // to parse JSON bodies

// ✅ New API endpoint
// app.get('/api/hello', (req, res) => {
//   res.json({ message: 'Hello from your new API endpoint!' });
// });

// You can add more endpoints like POST, PUT, DELETE here

// app.get('/api/traffic', (req, res) => {
//   console.log("Traffic endpoint was called");
//   res.json({ message: 'traffic  alert endpoint!' });
// });

app.get('/api/overspeed', (req, res) => {
  console.log("overspeed entry endpoint was called");
  res.json({ message: 'overspeed entry alert endpoint!' });
});


app.get('/api/unauthorized', (req, res) => {
  console.log("Unauthorized entry endpoint was called");
  res.json({ message: 'Unauthorized entry alert endpoint!' });
});


app.post('/api/overspeed', (req, res) => {
  const vehicleData = req.body; // get JSON from client
  console.log("Received overspeed POST data:", vehicleData);

  // You can process the vehicleData here, e.g., save to DB, check speed limits, etc.

  res.status(201).json({
    message: 'Overspeed data received successfully!',
    received: vehicleData
  });
});

app.post('/api/unauthorized', (req, res) => {
  const vehicleData = req.body; // get JSON from client
  console.log("Received Unauthorized entry POST data:", vehicleData);

  res.status(201).json({
    message: 'Unauthorized entry data received successfully!',
    received: vehicleData
  });
});


app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});



