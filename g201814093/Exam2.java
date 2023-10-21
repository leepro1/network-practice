package g201814093;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Exam2 {
	public static void main(String[] args) throws IOException {
		for (int outCnt = 0; outCnt < 3; outCnt++) {
			Reader reader = new FileReader("a.txt");
			while (true) {
				int ch = reader.read();
				if (ch < 0)
					break;
				System.out.print((char) ch);
			}
			System.out.println();
			reader.close();
		}
	}
}
