class PicoDashboard {
    constructor() {
        this.ledState = false;
        this.startAutoUpdate();
    }
    
    log(message) {
        const logDiv = document.getElementById('log');
        const timestamp = new Date().toLocaleTimeString();
        logDiv.innerHTML = `<div>[${timestamp}] ${message}</div>` + logDiv.innerHTML;
        
        // Keep only last 10 entries
        const entries = logDiv.children;
        while (entries.length > 10) {
            logDiv.removeChild(entries[entries.length - 1]);
        }
    }
    
    async updatePressure() {
        try {
            const response = await fetch('/api/pressure');
            const data = await response.json();
            
            document.getElementById('pressure-value').textContent = data.pressure;
            this.log(`Pressure updated: ${data.pressure}%`);
        } catch (error) {
            this.log(`Error fetching pressure: ${error.message}`);
            document.getElementById('pressure-value').textContent = 'Error';
        }
    }

    
    startAutoUpdate() {
        // Update pressure every 2 seconds
        setInterval(() => {
            this.updatePressure();
        }, 2000);
        
        // Initial update
        this.updatePressure();
    }
}

// Global functions for HTML onclick events
let dashboard;

window.onload = function() {
    dashboard = new PicoDashboard();
};

function updatePressure() {
    dashboard.updatePressure();
}