package chap09;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class GuguClient3 {
	static Random random = new Random();

	static class GuguTask implements Runnable {
		@Override
		public void run() {
			final String HOST = "localhost";
			final int PORT = 9090;
			try (Socket socket = new Socket(HOST, PORT)) {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				for (int i = 0; i < 10; ++i) {
					int a = random.nextInt(9) + 1;
					int b = random.nextInt(9) + 1;
					Gugu gugu = new Gugu(a, b);
					out.writeObject(gugu);
					out.flush();
					gugu = (Gugu) in.readObject();
					System.out.printf("%d x %d = %d\n", gugu.getA(), gugu.getB(), gugu.getResult());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; ++i)
			new Thread(new GuguTask()).start();
	}
}
