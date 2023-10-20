package b201814093;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Exam6 {
	public static void main(String[] args) throws IOException {
		Reader reader = new InputStreamReader(new BufferedInputStream(new FileInputStream("out5.txt")));
		Writer writer = new OutputStreamWriter(new BufferedOutputStream(System.out));
		while (true) {
			int ch = reader.read();
			if (ch < 0)
				break;
			writer.write(ch);
		}
		reader.close();
		writer.close();
	}
}
