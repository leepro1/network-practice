package chap09;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class StudentServer {
	public static void main(String[] args) {
		final int PORT = 10003;
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					Student student = new Student("201814093", "이희주");
					out.writeObject(student);
					out.flush();
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
