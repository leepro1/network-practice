package chap09;

import java.io.ObjectInputStream;
import java.net.Socket;

public class StudentClient {
	public static void main(String[] args) throws Exception {
		final String HOST = "localhost";
		final int PORT = 10003;

		try (Socket socket = new Socket(HOST, PORT)) {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Student student = (Student) in.readObject();
			System.out.printf("%s %s\n", student.getStudentId(), student.getName());
		}
	}
}
