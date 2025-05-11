const container = document.querySelector('.container');
const search = document.querySelector('.search-box button');
const weatherBox = document.querySelector('.weather-box');
const weatherDetails = document.querySelector('.weather-details');
const error = document.querySelector('.not-found');

search.addEventListener('click', () => {
 
    const apiKey = 'a09dfb46166fef6b2f9fd33d751cba46';
    const city = document.querySelector('.search-box input').value;

    if(city === '') {
        alert('Please enter a city name');
        return;
    }

    fetch(`https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`).then(response => {
        return response.json();
    }).then(data => {
        if(data.cod === '404') {
            container.style.height = '400px';
            weatherBox.style.display = 'none';
            weatherDetails.style.display = 'none';
            error.style.display = 'block';
            error.classList.add('fade-in');
            return;
        }

        error.style.display = 'none';
        error.classList.remove('fade-in');

        const img = document.querySelector('.weather-box img');
        const temp = document.querySelector('.weather-box .temperature');
        const desc = document.querySelector('.weather-box .description');
        const humid = document.querySelector('.weather-details .humidity span');
        const wind = document.querySelector('.weather-details .wind span');

        switch(data.weather[0].main) {
            case 'Clear':
                img.src = 'imgs/clear.png';
                break;
            case 'Clouds':
                img.src = 'imgs/cloud.png';
                break;
            case 'Rain':
                img.src = 'imgs/rain.png';
                break;
            case 'Snow':
                img.src = 'imgs/snow.png';
                break;
            case 'Haze':
                img.src = 'imgs/haze.png';
                break;
            default:
                img.src = '';
        }

        temp.innerHTML = `${parseInt(data.main.temp - 273.15)}&deg;C`;
        desc.innerHTML = `${data.weather[0].description}`;
        humid.innerHTML = `${data.main.humidity}%`;
        wind.innerHTML = `${data.wind.speed} Km/h`;

        weatherBox.style.display = '';
        weatherDetails.style.display = '';
        weatherBox.classList.add('fade-in');
        weatherDetails.classList.add('fade-in');
        container.style.height = '600px';

    }).catch(err => {
        console.log(err);
    });
});