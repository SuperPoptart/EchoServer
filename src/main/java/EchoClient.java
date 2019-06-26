import java.io.IOException;
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

		if (port < 81) {
			throw new Exception("Port must be greater than 80 and less than " + Short.MAX_VALUE);
		}
	}
	
	public void talkToServer(String host, short port) {
		try {
			Socket socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
