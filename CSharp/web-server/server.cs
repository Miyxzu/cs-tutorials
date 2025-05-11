namespace web_server;

using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
public static class Server
{
    // private static HttpListener listener;
    public static readonly int maxSimultaneousConnections = 20;
    private static readonly Semaphore sem = new(maxSimultaneousConnections, maxSimultaneousConnections);

    public static void Start()
    {
        List<IPAddress> localHostIPs = getLocalHostIPs();
        HttpListener listener = InitializeListener(localHostIPs);
        
        Console.WriteLine("Server starting...");
        listener.Start();
        Console.WriteLine("Server started!");

        Task.Run(() => {
            try
            {
                RunServer(listener);
            }
            catch (Exception e)
            {
                Console.WriteLine($"Server error: {e.Message}");
            }
        });
    }
    
    private static List<IPAddress> getLocalHostIPs()
    {
        IPHostEntry host;
        host = Dns.GetHostEntry(Dns.GetHostName());
        List<IPAddress> ret = [.. host.AddressList.Where(ip => ip.AddressFamily == AddressFamily.InterNetwork)];

        return ret;
    }

    private static HttpListener InitializeListener(List<IPAddress> localhostIPs)
    {
        HttpListener listener = new();
        listener.Prefixes.Add("http://localhost:8080/");

        localhostIPs.ForEach(ip => {
            Console.WriteLine("Listening on IP: http://" + ip.ToString() + ":8080/");
            listener.Prefixes.Add("http://" + ip.ToString() + ":8080/");
        });

        return listener;
    }

    private static void Start(HttpListener listener)
    {
        listener.Start();
        Task.Run(() => RunServer(listener));
    }

    private static void RunServer(HttpListener listener)
    {
        while (true)
        {
            sem.WaitOne();
            StartConnectionListener(listener);
        }
    }

    private static async void StartConnectionListener(HttpListener listener)
    {
        HttpListenerContext context = await listener.GetContextAsync();

        sem.Release();

        String response = "Hello World!";
        byte[] encode = Encoding.UTF8.GetBytes(response);
        context.Response.ContentLength64 = encode.Length;
        context.Response.OutputStream.Write(encode, 0, encode.Length);
        context.Response.OutputStream.Close();
    }
}
