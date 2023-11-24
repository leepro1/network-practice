import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam1Server {
	public static void main(String[] args) {
		final int PORT = 10003;
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					Reader reader = new InputStreamReader(socket.getInputStream(), "UTF-8");
					StringBuilder result = new StringBuilder();
					while (true) {
						int c = reader.read();
						if (c == -1)
							break;
						result.append((char) c);
					}
					System.out.println(result.toString());

				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
