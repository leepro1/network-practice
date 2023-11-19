package chap09;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentIOTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		final String fileName = "student.dat";
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)))) {
			Student student = new Student("201814093", "이희주");

			// 3번 출력
			for (int i = 0; i < 3; i++) {
				out.writeObject(student);
			}
		}

		try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
			// 몇번 입력 받았는지 모른다.
			while (true) {
				Student student = (Student) in.readObject();
				System.out.printf("%s %s\n", student.getStudentId(), student.getName());
			}
		} catch (EOFException e) {
		}
	}
}
