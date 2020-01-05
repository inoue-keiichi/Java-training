package ch14.ex01;

public class ThreadName extends Thread {

	public static void main(String[] args) {
		new ThreadName().start();
		System.out.println(ThreadName.currentThread().getName());

		ThreadName.currentThread().setName("change");
		System.out.println(ThreadName.currentThread().getName());
	}
}
