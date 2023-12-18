import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Exam5Server {
	public static void main(String[] args) {
		final int PORT = 7070;

		try (DatagramSocket socket = new DatagramSocket(PORT)) {
			while (true) {
				// receive
				byte[] data1 = new byte[512];
				DatagramPacket packet1 = new DatagramPacket(data1, data1.length);
				socket.receive(packet1);
				ObjectInputStream in = new ObjectInputStream(
						new ByteArrayInputStream(packet1.getData(), 0, packet1.getLength()));
				Student student = (Student) in.readObject();
				student.setName("이희주");

				InetAddress remoteAddr = packet1.getAddress(); // 답장 할 주소
				int remotePort = packet1.getPort(); // 답장 할 포트 주소

				// send
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(bout);
				out.writeObject(student);
				byte[] data2 = bout.toByteArray();
				DatagramPacket packet2 = new DatagramPacket(data2, data2.length, remoteAddr, remotePort);
				socket.send(packet2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
