import network
import time
import os
import urequests
import machine
from umqtt.simple import MQTTClient
# from pythonping import ping
# from dotenv import load_dotenv

# load_dotenv()

# Get networking and aws stuff from .env
SSID = 'hakone'
PASS = 'passtest123'
# CLIENT_ID = os.getenv('AWS_CLIENT_ID')
AWS_ENDPOINT = 'https://a3ksxjwqr1v19j-ats.iot.us-east-1.amazonaws.com'

PUB_TOPIC = b'/pressure'

# Wifi Connection Setup
def wifi_connect():
    print('Connecting to wifi...')
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    wlan.connect(SSID, PASS)
    while wlan.isconnected() == False:
        print('Waiting for connection...')
        time.sleep(1)
    print('Connection details: %s' % str(wlan.ifconfig()))

def getPressureSensorData():
    sensor = machine.ADC(machine.Pin(27))
    pressure = int(sensor.read_u16() / 65535 * 100)  # Convert to percentage
    return pressure

with open('/certs/squash_game.key.der', 'rb') as f:
    DEV_KEY = f.read()
with open('/certs/squash_game.cert.der', 'rb') as f:
    DEV_CRT = f.read()

mqtt = MQTTClient(
    client_id='squash_game',
    server=AWS_ENDPOINT,
    port=8883,
    keepalive=5000,
    ssl=True,
    ssl_params={
        'key': DEV_KEY,
        'cert': DEV_CRT,
        'server_side': False
    }
)

# wifi_connect()

# while True:
#     pressure = getPressureSensorData()
#     print('Pressure Sensor Data: %d%%' % pressure)
#     time.sleep(.1)