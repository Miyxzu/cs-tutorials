import network
import socket
import time
import machine
import ujson

SSID = 'ssid'
PASS = 'password'

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

def getPressureSensorData():
    sensor = machine.ADC(machine.Pin(27))
    return int(sensor.read_u16() / 65535 * 100)

def serve_file(filename):
    try:
        with open(filename, 'r') as f:
            return f.read()
    except:
        return None

def handle_request(request):
    try:
        # Parse the request line
        lines = request.split('\n')
        method, path, _ = lines[0].split()
        
        # API Routes
        if path == '/api/pressure':
            pressure = getPressureSensorData()
            response_body = ujson.dumps({'pressure': pressure, 'timestamp': time.time()})
            headers = 'HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nAccess-Control-Allow-Origin: *\r\n\r\n'
            return headers + response_body
        
        # Static File Routes
        elif path == '/' or path == '/ClickerGame.html':
            content = serve_file('ClickerGame.html')
            if content:
                headers = 'HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n'
                return headers + content
                
        elif path == '/ClickerGame.js':
            content = serve_file('ClickerGame.js')
            if content:
                headers = 'HTTP/1.1 200 OK\r\nContent-Type: application/javascript\r\n\r\n'
                return headers + content
                
        
        # 404 Not Found
        return 'HTTP/1.1 404 Not Found\r\n\r\n<h1>404 - Page Not Found</h1>'
        
    except Exception as e:
        print('Error handling request:', e)
        return 'HTTP/1.1 500 Internal Server Error\r\n\r\n<h1>500 - Server Error</h1>'

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
            print(f'Request from {addr}: {request.split()[1] if request else "Invalid"}')
            
            response = handle_request(request)
            cl.send(response.encode() if isinstance(response, str) else response)
            cl.close()
            
        except Exception as e:
            if cl:
                cl.close()
            print('Connection error:', e)

try:
    ip = wifi_connect()
    start_server(ip)
except KeyboardInterrupt:
    print('Server stopped')