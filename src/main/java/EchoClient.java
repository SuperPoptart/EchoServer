import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			throw new IllegalArgumentException("Invalid command arguments");
		}

		String host = args[0];
		short port = -1;

		try {
			port = Short.parseShort(args[1]);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("invalid port number");
		}

		if (port < 1025) {
			throw new Exception("Port must be greater than 1024 and less than " + Short.MAX_VALUE);
		}
		talkToServer(host, port);
	}

	public static void talkToServer(String host, short port) throws UnknownHostException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Socket socket = new Socket(host, port);
		byte[] message = new byte[200];

		do {
			System.out.println("What would you like to send?");
			String outGoingMessage = in.readLine();
			System.out.println("Client says: " + outGoingMessage);
			socket.getOutputStream().write(String.format("%200s", outGoingMessage).getBytes());
			socket.getInputStream().read(message);
			String output = new String(message).trim();
			System.out.println("Echo: " + output);
//			System.out.println("Server says: " + new String(socket.getInputStream().readAllBytes()).trim());
		} while (true);
//		socket.close();
	}
}
