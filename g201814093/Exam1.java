package g201814093;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Exam1 {
	public static void main(String[] args) throws IOException {
		Writer writer = new FileWriter("a.txt");
		writer.write("IT융합자율학부\n");
		writer.write("201814093\n");
		writer.write("이희주");
		writer.close();
	}
}
