package chap05;

class SumTaskE implements Runnable {
	int from, to;
	long result;

	public SumTaskE(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public void run() {
		long sum = 0;
		for (int i = from; i <= to; ++i)
			sum += i;
		result = sum;
	}

	public long getResult() {
		return result;
	}
}

public class Example2e {
	public static void main(String[] args) throws InterruptedException {
		int from = 1, to = 50000000;
		SumTaskE sumTask = new SumTaskE(from, to);
		Thread thread = new Thread(sumTask);
		thread.start();
		thread.join();
		
		System.out.print(from + " 부터 " + to + " 까지 합계는 ");
		System.out.print(sumTask.getResult());
	}
}