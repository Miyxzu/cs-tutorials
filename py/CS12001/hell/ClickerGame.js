let score = 0;
let lastPressure = null;
let pressure = 0;

// variables relating to upgrades
let up1 = false, up2 = false, up3 = false;

function addpointscauseidc() { 
  score += 1;
  document.getElementById("scoreTag").innerHTML = "Current Score: " + score;
}

function enableFirst() {
  if (up1 == false) {
    score -= 50;
    document.getElementById("scoreTag").innerHTML = "Current Score: " + score;
    up1 = true;
    document.getElementById("u1").innerHTML = "Upgrade sold out";
  } else {
    console.log("This upgrade has already purchased");
  }
}

function firstUpgrade() {
  if (up1 == true) {
    while (true) {
      setInterval(() => {
        score += 1;
      }, 1000);
    }
  }
}

function enableSecond() {
  if (up2 == false) {
    score -= 150;
    document.getElementById("scoreTag").innerHTML = "Current Score: " + score;
    up2 = true;
    document.getElementById("u2").innerHTML = "Upgrade sold out";
  } else {
    console.log("This upgrade has already purchased");
  }
}

function secondUpgrade() {
  if (up2 == true) {
    while (true) {
      setInterval(() => {
        score += 10;
      }, 3000);
    }
  }
}

function enableThird() {
  if (up3 == false) {
    score -= 550;
    document.getElementById("scoreTag").innerHTML = "Current Score: " + score;
    up3 = true;
    document.getElementById("u3").innerHTML = "Upgrade sold out";
  } else {
    console.log("This upgrade has already purchased");
  }
}

function thirdUpgrade() {
  if (up3 == true) {
    while (true) {
      setInterval(() => {
        score += 50;
      }, 1000);
    }
  }
}

async function updatePressure() {
  try {
    const response = await fetch("/api/pressure");
    const data = await response.json();

    pressure = data.pressure;

    if (lastPressure !== null && pressure - lastPressure > 50) {
      score += 1;
      document.getElementById("title").innerHTML = "Larry has been pressed";
      document.getElementById("scoreTag").innerHTML = "Current Score: " + score;
    }
    lastPressure = pressure;

    document.getElementById("percent").innerHTML = data.pressure + "%";
  } catch (error) {
    console.log("Error fetching pressure:", error.message);
    document.getElementById("scoreTag").innerHTML = "Error: See console for details.";
  }
}

// Update pressure every second
setInterval(updatePressure, 1000);

// Initial pressure update on page load
window.onload = function () {
  updatePressure();
  firstUpgrade();
  secondUpgrade();
  thirdUpgrade();
};
