package chap10;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer2 {
	public static void main(String[] args) {
		final int PORT = 7070;
		int count = 0;

		try (DatagramSocket socket = new DatagramSocket(PORT)) {
			byte[] data = new byte[512];
			while (true) {
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
				ObjectInputStream in = new ObjectInputStream(bin);
				Message message = (Message) in.readObject();

				System.out.printf("%s %d %s %d\n", packet.getAddress(), packet.getPort(), message.getText(), ++count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
