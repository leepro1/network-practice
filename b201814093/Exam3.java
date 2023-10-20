package b201814093;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class Exam3 {
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
		InputStream in = new BufferedInputStream(new FileInputStream("out2.txt"));
		OutputStream out = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("out2.txt.gz")));
		copyStream(in, out);
	}
}
