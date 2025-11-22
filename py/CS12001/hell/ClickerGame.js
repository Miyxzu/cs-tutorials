let score = 0;
let lastPressure = null;
let pressure = 0;
let currentTime = 0;

// variables relating to upgrades
let up1 = false,
  up2 = false,
  up3 = false;

function updateScore(amount) {
  score += amount;
  document.getElementById("scoreTag").innerHTML = "Current Score: " + score;
}

function checkScorePossible(cost) { 
  if (score >= cost) {
    return true;
  } else {
    return false;
  }
}

function addpointscauseidc() {
  updateScore(25);
}

function enableFirst() {
  if (up1 == false) {
    if (checkScorePossible(50)) {
      updateScore(-50);
      up1 = true;
      document.getElementById("u1").innerHTML = "Upgrade sold out";
      document.getElementById("shopIndicator").innerHTML = "First upgrade purchased!";
    } else {
      document.getElementById("shopIndicator").innerHTML = "Not enough score for first upgrade.";
    }
  } else {
    document.getElementById("shopIndicator").innerHTML = "This upgrade has already purchased";
  }
}

function enableSecond() {
  if (up2 == false) {
    if (checkScorePossible(150)) {
      updateScore(-150);
      up2 = true;
      document.getElementById("u2").innerHTML = "Upgrade sold out";
      document.getElementById("shopIndicator").innerHTML = "Second upgrade purchased!";
    } else {
      document.getElementById("shopIndicator").innerHTML = "Not enough score for second upgrade.";
    }
  } else {
    document.getElementById("shopIndicator").innerHTML = "This upgrade has already purchased";
  }
}

function enableThird() {
  if (up3 == false) {
    if (checkScorePossible(500)) {
      updateScore(-500);
      up3 = true;
      document.getElementById("u3").innerHTML = "Upgrade sold out";
      document.getElementById("shopIndicator").innerHTML = "Third upgrade purchased!";
    } else {
      document.getElementById("shopIndicator").innerHTML = "Not enough score for third upgrade.";
    }
  } else {
    document.getElementById("shopIndicator").innerHTML = "This upgrade has already purchased";
  }
}

async function updateClickerGame() {
  currentTime++;

  try {
    const response = await fetch("/api/pressure");
    const data = await response.json();

    pressure = data.pressure;

    if (lastPressure !== null && pressure - lastPressure > 50) {
      updateScore(1);
      document.getElementById("title").innerHTML = "Larry has been pressed";
    }
    lastPressure = pressure;

    document.getElementById("percent").innerHTML = data.pressure + "%";
  } catch (error) {
    console.log("Error fetching pressure:", error.message);
    document.getElementById("scoreTag").innerHTML =
      "Error: See console for details.";
  }

  if (up1) {
    updateScore(1);
  }

  if (up2 && currentTime % 3 === 0) {
    updateScore(10);
  }

  if (up3) {
    updateScore(50);
  }
}

// Update pressure every second
setInterval(updateClickerGame, 1000);

// Initial pressure update on page load
window.onload = function () {
  updateClickerGame();
};
