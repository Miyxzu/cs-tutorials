let score = 0;
let lastPressure = null;
let pressure = 0;

async function updatePressure() {
  try {
    const response = await fetch("/api/pressure");
    const data = await response.json();

    pressure = data.pressure;
    testPress();
    document.getElementById("scoreTag").innerHTML = data.pressure;
  } catch (error) {
    // Fix: Remove 'this.log' and use console.log instead
    console.log("Error fetching pressure:", error.message);
    document.getElementById("scoreTag").innerHTML = "Error";
  }
}

function testPress() { 
    // Use the actual pressure value instead of just incrementing
    if (lastPressure !== null && (pressure - lastPressure) > 20) {
      console.log("Pressure increased from", lastPressure, "to", pressure);
      score += 1;
      document.getElementById("title").innerHTML = "Pressure increased! Score: " + score;
      // Handle pressure increase here
    }
    lastPressure = pressure;
    console.log("Pressure updated:", pressure);
}

function call_game_logic() {
  document.getElementById("title").innerHTML = "Squash has been pressed";
  // Also update pressure when button is clicked
  updatePressure();
}

// Auto-update pressure every 2 seconds like the dashboard
setInterval(updatePressure, 1000);

// Initial pressure update when page loads
window.onload = function () {
  updatePressure();
};