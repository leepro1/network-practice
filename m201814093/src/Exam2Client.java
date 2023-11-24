import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class Exam2Client {
	public static void main(String[] args) throws IOException {
		final String HOST = "localhost";
		final int PORT = 10003;

		try (Socket socket = new Socket(HOST, PORT)) {
			Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");

			for(int i=0;i<5;i++) {
				out.write("201814093 ");
				out.write("이희주\r\n");
				out.flush();
			}
		}
	}
}
