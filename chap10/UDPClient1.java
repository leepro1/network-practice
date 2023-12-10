package chap10;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPClient1 {
	public static void main(String[] args) {
		final String HOST = "localhost"; 
		final int PORT = 7070; //받는 이(server) port번호
		
		try (DatagramSocket socket = new DatagramSocket(0)) { //보낼 때에는 비어있는 포트번호 아무거나 사용해도 되므로 0
			InetAddress hostAddress = InetAddress.getByName(HOST); //서버주소 객체
			for (int i = 0; i < 10; ++i) {
				String s = String.format("%tT", new Date());
				System.out.printf("sending %d %s\n", i, s);
				byte[] data = s.getBytes("US-ASCII"); //보낼 data를 바이트 배열로 만든다. + 한글이 있었으면 UTF-8로 인코딩
				DatagramPacket packet = new DatagramPacket(data, data.length, hostAddress, PORT);
				socket.send(packet);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}