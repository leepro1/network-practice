package chap02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Example1a {
	public static void main(String[] args) throws IOException {
		final String filePath = "out.txt";
		
		//쓰기
		FileOutputStream out = new FileOutputStream(filePath);
		byte[] a = new byte[] { 'h', 'e', 'l', 'l', 'o', 32, 119, 111, 114, 108, 100 }; //8비트이고 문자로 썼어도 숫자다. 즉 ASKII를 이용하여 작성
		out.write(a);
		out.close();
		
		//읽기
		FileInputStream in = new FileInputStream(filePath);
		byte[] buffer = new byte[100];
		int count = in.read(buffer);
		for (int i = 0; i < count; ++i)
			System.out.print((char) buffer[i]);
		in.close();
	}
}