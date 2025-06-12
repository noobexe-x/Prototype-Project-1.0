package cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class cash
{
    public static void main(String[] args) throws IOException 
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/run", (exchange -> 
        {
            String query = exchange.getRequestURI().getQuery(); // input=xxx
            String input = "";
            if (query != null && query.startsWith("input=")) 
            {
                input = java.net.URLDecoder.decode(query.substring(6), "UTF-8");
            }

            ProcessBuilder pb = new ProcessBuilder("C:\\hal\\JV15\\cash\\cash.exe", input);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader cobolOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = cobolOutput.readLine()) != null) 
            {
                output.append("chinese cash ").append(line).append("\n");
            }

            byte[] response = output.toString().getBytes("UTF-8");
            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=utf-8");
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        }));

        server.setExecutor(null);
        server.start();
        System.out.println("open： http://localhost:8000/run?input=123");
    }
}
