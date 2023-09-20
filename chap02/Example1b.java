package chap02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Example1b {
	public static void main(String[] args) throws IOException {
		final String filePath = "out.txt";
		
		FileOutputStream out = new FileOutputStream(filePath);
		byte[] a = new byte[] { 'h', 'e', 'l', 'l', 'o', 32, 119, 111, 114, 108, 100 };
		for (int i = 0; i < a.length; ++i)
			out.write(a[i]);
		out.close();
		
		FileInputStream in = new FileInputStream(filePath);
		while (true) {
			int b = in.read(); //바이트뿐 아니라 -1(파일의 끝)도 리턴하기 때문에 byte가 아닌 int타입
			if (b < 0)
				break;
			System.out.print((char) b);
		}
		in.close();
	}
}