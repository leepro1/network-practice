package b201814093;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Exam5 {
	public static void main(String[] args) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out5.txt"), "UTF-8"));
		for (int i = 0; i < 10; ++i)
			writer.write("201814093 이희주\n");
		writer.close();
	}
}
