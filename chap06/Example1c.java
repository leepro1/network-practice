package chap06;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Example1c {
	static Queue<Integer> queue = new ArrayDeque<Integer>();

	static class Producer extends Thread {
		final int count = 100;
		Random random = new Random();

		@Override
		public void run() {
			try {
				for (int i = 0; i < count; ++i) {
					Thread.sleep(10);
					int n = random.nextInt(100);
					synchronized (queue) {
						queue.add(n);
					}
				}
				synchronized (queue) {
					queue.add(-1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class Consumer extends Thread {
		Random random = new Random();

		@Override
		public void run() {
			try {
				int n;
				while (true) {
					Thread.sleep(10);
					synchronized (queue) {
						n = queue.remove();
					}
					if (n < 0)
						break;
					int sum = 0;
					for (int i = 1; i <= n; ++i)
						sum += i;
					System.out.printf("%d: %d\n", n, sum);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Producer().start();
		new Consumer().start();
	}
}