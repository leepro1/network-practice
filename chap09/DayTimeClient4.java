package chap09;

import java.io.ObjectInputStream;
import java.net.Socket;

public class DayTimeClient4 {
	public static void main(String[] args) throws Exception {
		final String HOST = "localhost";
		final int PORT = 13;

		try (Socket socket = new Socket(HOST, PORT)) {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Message msg = (Message) in.readObject();
			System.out.printf("%s %s\n", msg.getValue(), msg.getDate());
		}
	}
}
