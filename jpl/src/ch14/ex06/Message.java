package ch14.ex06;

public class Message {
	public synchronized void suspend() throws InterruptedException {
		wait();
	}

	public synchronized void print(int time) {
		System.out.print(time + " ");
		if (time % 15 == 0) {
			System.out.print("America　");
		} else if (time % 7 == 0) {
			System.out.print("Canada　");
		}
		System.out.println("");
		notifyAll();
	}
}
