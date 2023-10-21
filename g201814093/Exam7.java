package g201814093;

import java.util.ArrayList;

public class Exam7 {
	 public static void main(String[] args) {
	        ArrayList<Integer> sharedList = new ArrayList<>();

	        Runnable addTask = () -> {
	            synchronized (sharedList) {
	                for (int i = 1; i <= 10; i++) {
	                    sharedList.add(i);
	                }
	                sharedList.notify();
	            }
	        };

	        Thread thread1 = new Thread(addTask);
	        Thread thread2 = new Thread(addTask);
	        Thread thread3 = new Thread(addTask);

	        thread1.start();
	        thread2.start();
	        thread3.start();

	       
	        synchronized (sharedList) {
	            try {
	                sharedList.wait();
	                sharedList.wait();
	                sharedList.wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        for (Integer num : sharedList) {
	            System.out.print(num + " ");
	        }
	    }
}