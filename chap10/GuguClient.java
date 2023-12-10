package chap10;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class GuguClient {
	public static void main(String[] args) {
		final String HOST = "localhost";
		final int PORT = 7071;
		
		try (DatagramSocket socket = new DatagramSocket(0)) {
			socket.setSoTimeout(3000); //3초동안 전달안되면 timeout 에러 처리
			InetAddress hostAddress = InetAddress.getByName(HOST);
			Random random = new Random();
			for (int i = 0; i < 100; ++i) {
				Thread.sleep(10); 
				
				// send
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(bout);
				int a = random.nextInt(8) + 1;
				int b = random.nextInt(8) + 1;
				Gugu gugu1 = new Gugu(a, b);
				out.writeObject(gugu1);
				byte[] data1 = bout.toByteArray();
				DatagramPacket packet1 = new DatagramPacket(data1, data1.length, hostAddress, PORT);
				socket.send(packet1); 
				
				// receive
				byte[] data2 = new byte[512];
				DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
				socket.receive(packet2);
				ObjectInputStream in = new ObjectInputStream(
						new ByteArrayInputStream(packet2.getData(), 0, packet2.getLength()));
				Gugu gugu2 = (Gugu) in.readObject();
				System.out.printf("%3d: %d x %d = %d\n", i, gugu2.getA(), gugu2.getB(), gugu2.getResult());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
