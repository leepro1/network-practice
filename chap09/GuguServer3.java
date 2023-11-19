package chap09;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuguServer3 {
	public static void main(String[] args) {
		final int PORT = 9090;
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			ExecutorService executor = Executors.newFixedThreadPool(200);
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					executor.submit(new GuguTask(socket));
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	static class GuguTask implements Runnable {
		Socket socket;

		public GuguTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (Socket autoClose = socket) {
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				while (true) {
					Gugu gugu = (Gugu) in.readObject();
					gugu.result = gugu.a * gugu.b;
					out.writeObject(gugu);
					out.flush();
				}
			} catch (IOException ex) {
			} catch (ClassNotFoundException e) {
			}
		}
	}
}
