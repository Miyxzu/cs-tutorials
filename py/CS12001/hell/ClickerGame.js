let score = 0;
let lastPressure = null;
let pressure = 0;

async function updatePressure() {
  try {
    const response = await fetch("/api/pressure");
    const data = await response.json();

    pressure = data.pressure;
    // testPress();

    // Use the actual pressure value instead of just incrementing
    if (lastPressure !== null && (pressure - lastPressure > 50)) {
      console.log("Pressure increased from", lastPressure, "to", pressure);
      score += 1;
      document.getElementById("title").innerHTML = "Larry has been pressed";
      document.getElementById("scoreTag").innerHTML = ("Current Score: " + score);
      // Handle pressure increase here
    }
    lastPressure = pressure;
    console.log("Pressure updated:", pressure);

    document.getElementById("percent").innerHTML = data.pressure + '%';
  } catch (error) {
    // Fix: Remove 'this.log' and use console.log instead
    console.log("Error fetching pressure:", error.message);
    document.getElementById("scoreTag").innerHTML = "Error";
  }
}

// Update pressure every second
setInterval(updatePressure, 1000);

// Initial pressure update on page load
window.onload = function () {
  updatePressure();
};
