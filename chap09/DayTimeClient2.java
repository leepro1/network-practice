package chap09;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DayTimeClient2 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		final String HOST = "localhost";
		final int PORT = 13;
		
		try (Socket socket = new Socket(HOST, PORT)) {
			Reader reader = new InputStreamReader(socket.getInputStream(), "UTF-8");
			StringBuilder result = new StringBuilder();
			while (true) {
				int c = reader.read();
				if (c == -1)
					break;
				result.append((char) c);
			}
			System.out.println(result.toString());
		}
	}
}
