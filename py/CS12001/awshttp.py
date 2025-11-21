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
SSID = b'Vodafone-A340'
PASS = b'Ac16051992ac!'
AWS_ENDPOINT = b'a3ksxjwqr1v19j-ats.iot.us-east-1.amazonaws.com'
CLIENT_ID = b'squash_game'
PUB_TOPIC = b'/' + CLIENT_ID + '/pressure'

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

# Read Private Key and Cert into vars
with open('/certs/squash_game.key.der', 'rb') as f:
    DEV_KEY = f.read()
with open('/certs/squash_game.cert.der', 'rb') as f:
    DEV_CRT = f.read()

# MQTT Client Setup code that i just copy pasted cause it works
mqtt = MQTTClient(
    client_id=CLIENT_ID,
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

wifi_connect()
mqtt.connect()
print("the mqtt works and aws is connected")

while True:
    currPressure = getPressureSensorData()
    message = b'{"Pressure":%s}' % currPressure
    print("Pressure: %d%%" % currPressure)
    mqtt.publish(topic=PUB_TOPIC, msg=message, qos=0)
    time.sleep(1)