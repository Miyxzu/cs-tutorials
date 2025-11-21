import network
import socket
import time
import machine # type: ignore
import ujson # type: ignore

SSID = 'ssid'
PASS = 'password123!'

# Connect to the internet
# (cause idk how else you would run a web server without it)
def wifi_connect():
    print('Connecting to wifi...')
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    wlan.config(pm = 0xa11140)  # Disable power saving
    wlan.connect(SSID, PASS)
    
    while wlan.isconnected() == False:
        print('Waiting for connection...')
        time.sleep(1)
        
    config = wlan.ifconfig()
    print('Connection details:', config)
    return config[0]

# Read and normalize pressure sensor data
def getPressureSensorData():
    sensor = machine.ADC(machine.Pin(27))
    return int(sensor.read_u16() / 65535 * 100)

# Open and serve necessary files
def serve_file(filename):
    try:
        if str(filename).endswith('.jpg'):
            with open(filename, 'rb') as f:
                return f.read()
        else:
            with open(filename, 'r') as f:
                return f.read()
    except:
        return None

def handle_request(request):
    try:
        # Parse the request line
        lines = request.split('\n')
        method, path, _ = lines[0].split()
        
        # Pressure API endpoint
        if path == '/api/pressure':
            pressure = getPressureSensorData()
            response_body = ujson.dumps({'pressure': pressure, 'timestamp': time.time()})
            headers = 'HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nAccess-Control-Allow-Origin: *\r\n\r\n'
            return headers + response_body
        
        # Paths to the frontend files
        elif path == '/' or path == '/clickerGame.html':
            content = serve_file('clickerGame.html')
            if content:
                headers = 'HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n'
                return headers + content
                
        elif path == '/clickerGame.js':
            content = serve_file('clickerGame.js')
            if content:
                headers = 'HTTP/1.1 200 OK\r\nContent-Type: application/javascript\r\n\r\n'
                return headers + content
            
        elif path == '/larry.jpg':
            content = serve_file('larry.jpg')
            if content:
                image_len = str(len(content))
                headers = 'HTTP/1.1 200 OK\r\nContent-Type: image/jpg\r\nContent-Length: ' + image_len + '\r\n\r\n'
                return ('binary', headers.encode() + content)
                
        
        # 404 Not Found
        return 'HTTP/1.1 404 Not Found\r\n\r\n<h1>404 - Page Not Found</h1>'
    
    # If error regarding request occurs
    except Exception as e:
        print('Error handling request:', e)
        return 'HTTP/1.1 500 Internal Server Error\r\n\r\n<h1>500 - Server Error</h1>'

# Main server loop
def start_server(ip):
    addr = socket.getaddrinfo('0.0.0.0', 8080)[0][-1]
    s = socket.socket()
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind(addr)
    s.listen(1)
    
    print('--------------------------------------------------')
    print(f'Web Server running at: http://{ip}:8080')
    print('--------------------------------------------------')

    while True:
        cl = None
        try:
            cl, addr = s.accept()
            request = cl.recv(1024).decode()
            print(f'Request: {request.split()[1] if request else "Invalid"}')
            
            response = handle_request(request)
            if isinstance(response, tuple) and response[0] == 'binary':
                cl.send(response[1])
            else:
                cl.send(response.encode() if isinstance(response, str) else response)
            
            cl.close()
            
        except Exception as e:
            if cl:
                cl.close()
            print('Connection error:', e)

# Starts server
try:
    start_server(wifi_connect())
except KeyboardInterrupt:
    print('Server stopped')