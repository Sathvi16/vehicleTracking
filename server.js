const express = require('express'); const app = express(); const port = 3000;

app.use(express.json()); // to parse JSON bodies

// ✅ New API endpoint // app.get('/api/hello', (req, res) => { // res.json({ message: 'Hello from your new API endpoint!' }); // });

// You can add more endpoints like POST, PUT, DELETE here

// app.get('/api/traffic', (req, res) => { // console.log("Traffic endpoint was called"); // res.json({ message: 'traffic alert endpoint!' }); // });

app.get('/api/overspeed', (req, res) => { console.log("overspeed entry endpoint was called"); res.json({ message: 'overspeed entry alert endpoint!' }); });

app.get('/api/unauthorized', (req, res) => { console.log("Unauthorized entry endpoint was called"); res.json({ message: 'Unauthorized entry alert endpoint!' }); });

app.post('/api/overspeed', (req, res) => { const vehicleData = req.body; // get JSON from client console.log("Received overspeed POST data:", vehicleData);

// You can process the vehicleData here, e.g., save to DB, check speed limits, etc.

res.status(201).json({ message: 'Overspeed data received successfully!', received: vehicleData }); });

app.post('/api/unauthorized', (req, res) => { const vehicleData = req.body; // get JSON from client console.log("Received Unauthorized entry POST data:", vehicleData);

res.status(201).json({ message: 'Unauthorized entry data received successfully!', received: vehicleData }); });

app.listen(port, () => { console.log(Server running at http://localhost:${port}); });
