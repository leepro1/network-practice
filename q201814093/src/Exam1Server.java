import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam1Server {
	public static void main(String[] args) throws ClassNotFoundException {
		final int PORT = 10003;
		
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					Student student = (Student)in.readObject();
					System.out.printf("%s %s\n", student.getStudentNo(), student.getName());
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
