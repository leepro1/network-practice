package b201814093;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Exam1 {
	public static void main(String[] args) throws IOException {

		OutputStream out = new BufferedOutputStream(new FileOutputStream(("out1.txt")));
		byte[] a = new byte[] { '2', '0', '1', '8', '1', '4', '0', '9', '3', '\n' };
		for (int i = 0; i < 10; ++i)
			out.write(a);
		out.close();
	}
}