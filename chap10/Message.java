package chap10;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	String text;
	Date now;
	int[] data = new int[100]; //사용 안함. 그냥 패킷크기 키우려고 넣은거임

	public Message(String text, Date now) {
		super();
		this.text = text;
		this.now = now;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}
}
