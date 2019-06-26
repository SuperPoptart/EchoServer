import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        short port = -1;

        try {
            port = Short.parseShort(args[0]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("invalid port number");
        }

        if (port < 1025) {
            throw new Exception("Port must be greater than 1024 and less than " + Short.MAX_VALUE);
        }
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {//should make the server keep running
            Socket socket = serverSocket.accept();

            byte[] statement = new byte[200];
            socket.getInputStream().read(statement);
            System.out.println("Received from client: " + new String(statement).trim());
            System.out.println("Sending to client: " + new String(statement).trim());
            socket.getOutputStream().write(statement);
        }
    }
}
