import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam3Server {
	public static void main(String[] args) {
		final int PORT = 10003;

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			ExecutorService executor = Executors.newFixedThreadPool(200);
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					executor.submit(new StudentTask(socket));
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	static class StudentTask implements Runnable {
		Socket socket;

		public StudentTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (Socket autoClose = socket) {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				Student student = (Student) in.readObject();
				System.out.println(student.getStudentNo() + " " + student.getName());
			} catch (IOException ex) {
			} catch (ClassNotFoundException e) {
			}
		}
	}
}
