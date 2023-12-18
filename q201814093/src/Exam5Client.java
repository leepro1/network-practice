import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Exam5Client {

	public static void main(String[] args) {
		final String HOST = "localhost";
		final int PORT = 7070;

		try (DatagramSocket socket = new DatagramSocket(0)) {
			InetAddress hostAddress = InetAddress.getByName(HOST);

			// send
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			Student student1 = new Student();
			student1.setStudentId("201814093");
			out.writeObject(student1);
			byte[] data1 = bout.toByteArray();
			DatagramPacket packet1 = new DatagramPacket(data1, data1.length, hostAddress, PORT);
			socket.send(packet1);

			// receive
			byte[] data2 = new byte[512];
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
			socket.receive(packet2);
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(packet2.getData(), 0, packet2.getLength()));
			Student student2 = (Student) in.readObject();
			System.out.println(student2.getStudentNo() + " " + student2.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}