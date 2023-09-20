package chap02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

public class Problem1 {

	static void copyStream(InputStream in, OutputStream out) throws IOException {
		while (true) {
			int b = in.read();
			if (b < 0)
				break;
			out.write(b);
		}
		in.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		var url = new URL("https://www.naver.com");
		var connection = (HttpURLConnection) url.openConnection();
		InputStream in1 = new BufferedInputStream(connection.getInputStream());
		OutputStream out1 = new BufferedOutputStream(new FileOutputStream("naver.html"));
		copyStream(in1, out1);

		InputStream in2 = new BufferedInputStream(new FileInputStream("naver.html"));
		OutputStream out2 = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("naver.html.gz")));
		copyStream(in2, out2);
	}
}
