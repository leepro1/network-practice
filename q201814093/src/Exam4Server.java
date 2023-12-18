import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Exam4Server {
	public static void main(String[] args) {
		final int PORT = 7070;

		try (DatagramSocket socket = new DatagramSocket(PORT)) {
			byte[] data = new byte[512];
			while (true) {
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				ByteArrayInputStream bin = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
				ObjectInputStream in = new ObjectInputStream(bin);
				Student student = (Student) in.readObject();
				System.out.println(student.getStudentNo() + " " + student.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
