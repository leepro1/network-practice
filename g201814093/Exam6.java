package g201814093;

import java.io.FileReader;
import java.io.Reader;

public class Exam6 {
	static class TestRunnable implements Runnable {
		@Override
        public void run() {
        	StringBuilder sb = new StringBuilder();
            try {
                Reader reader = new FileReader("a.txt");
                int ch;
                while ((ch = reader.read()) != -1) {
                    sb.append((char) ch);
                }
                reader.close();
                System.out.println(sb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; ++i) {
			Thread thread = new Thread(new TestRunnable());
			thread.start();
		}
	}
}
