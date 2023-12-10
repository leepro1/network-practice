package chap10;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPClient2 {
	public static void main(String[] args) {
		for (int i = 0; i < 100; ++i)
			new Thread(new SendTask()).start();
	}

	static class SendTask implements Runnable {
		@Override
		public void run() {
			final String HOST = "localhost";
			final int PORT = 7070;
			
			try (DatagramSocket socket = new DatagramSocket(0)) {
				InetAddress hostAddress = InetAddress.getByName(HOST);
				for (int i = 0; i < 10; ++i) {
					System.out.printf("sending %d\n", i);
					ByteArrayOutputStream bout = new ByteArrayOutputStream(); //객체 -> byte배열
					ObjectOutputStream out = new ObjectOutputStream(bout);
					out.writeObject(new Message("안녕하세요" + i, new Date()));
					byte[] data = bout.toByteArray();
					DatagramPacket packet = new DatagramPacket(data, data.length, hostAddress, PORT);
					socket.send(packet);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}