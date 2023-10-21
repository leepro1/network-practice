package g201814093;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.GZIPOutputStream;

public class Exam3 {
	public static void main(String[] args) throws IOException {
		Writer writer = new OutputStreamWriter(
				new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("b.txt.gz"))));
		for (int i = 0; i < 10; ++i)
			writer.write("안녕하세요\n");
		writer.close();
	}
}
