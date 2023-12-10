package chap10;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class UDPServer2c {
	static AtomicInteger count = new AtomicInteger(0);

	public static void main(String[] args) {
		final int PORT = 7070;
		ExecutorService executorService = Executors.newFixedThreadPool(500);
		try (DatagramSocket socket = new DatagramSocket(PORT)) {
			while (true) {
				byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				executorService.submit(new ReceiveTask(packet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class ReceiveTask implements Runnable {
		DatagramPacket packet;

		public ReceiveTask(DatagramPacket packet) {
			this.packet = packet;
		}

		@Override
		public void run() {
			try {
				ObjectInputStream in = new ObjectInputStream(
						new ByteArrayInputStream(packet.getData(), 0, packet.getLength()));
				Message message = (Message) in.readObject();
				System.out.printf("%s %d %s %d\n", packet.getAddress(), packet.getPort(), message.getText(),
						count.incrementAndGet());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
