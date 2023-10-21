package g201814093;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.zip.GZIPInputStream;

public class Exam4 {
	static void copyText(Reader reader, Writer writer) throws IOException {
		while (true) {
			int ch = reader.read();
			if (ch < 0)
				break;
			writer.write(ch);
		}
		reader.close();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		Reader reader = new InputStreamReader(
				new GZIPInputStream(new BufferedInputStream(new FileInputStream("b.txt.gz"))));
		Writer writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("c.txt")));
		copyText(reader, writer);
	}
}
