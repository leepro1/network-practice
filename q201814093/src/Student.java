import java.io.Serializable;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;

	String studentNo;
	String name;

	public Student() {
		super();
	}

	public Student(String studentNo, String name) {
		this.studentNo = studentNo;
		this.name = name;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentId(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
