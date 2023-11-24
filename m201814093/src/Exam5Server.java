import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam5Server {
	public static void main(String[] args) {
		final int PORT = 10003, BACKLOG = 200;
		try (ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG)) {
			ExecutorService executor = Executors.newFixedThreadPool(200);
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					executor.submit(new ExamTask(socket));
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	static class ExamTask implements Runnable {
		Socket socket;

		public ExamTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (Socket autoClose = socket){
				Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
				
				out.write("201814093 ");
				out.write("이희주\r\n");
				out.flush();
			} catch (Exception ex) {
			}
		}
	}
}
