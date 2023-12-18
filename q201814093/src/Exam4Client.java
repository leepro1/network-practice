import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Exam4Client {
	public static void main(String[] args) {
		final String HOST = "localhost";
		final int PORT = 7070;
		try (DatagramSocket socket = new DatagramSocket(0)) {
			InetAddress hostAddress = InetAddress.getByName(HOST);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(new Student("201814093", "이희주"));
			byte[] data = bout.toByteArray();
			DatagramPacket packet = new DatagramPacket(data, data.length, hostAddress, PORT);
			socket.send(packet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
