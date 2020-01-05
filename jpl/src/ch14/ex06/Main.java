package ch14.ex06;

public class Main {
	public static void main(String[] args) {

		final Message message = new Message();
		new MessageThread(message);
		new TimeThread(message);
	}
}
