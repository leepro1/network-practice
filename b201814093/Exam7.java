package b201814093;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.GZIPOutputStream;

public class Exam7 {
	public static void main(String[] args) throws IOException {
		Writer writer = new OutputStreamWriter(
				new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("out6.txt.gz"))), "euc-kr");
		for (int i = 0; i < 10000; ++i)
			writer.write("201814093 이희주\n");
		writer.close();
	}
}