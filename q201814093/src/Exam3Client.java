import java.io.ObjectOutputStream;
import java.net.Socket;

public class Exam3Client {
	public static void main(String[] args) throws Exception {
		final String HOST = "localhost";
		final int PORT = 10003;

		try (Socket socket = new Socket(HOST, PORT)) {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			Student student = new Student("201814093", "이희주");
			out.writeObject(student);
			out.flush();
		}
	}
}
