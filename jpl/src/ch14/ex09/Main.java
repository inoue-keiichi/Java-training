package ch14.ex09;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ThreadGroup group = new ThreadGroup("Group");
		new ShortLifeThread(group, 3000);
		new ShortLifeThread(group, 4000);
		new ShortLifeThread(group, 5000);
		new ShortLifeThread(group, 6000);
		ThreadGroup childGroup = new ThreadGroup(group, "ChildGroup");
		new ShortLifeThread(childGroup, 7000);
		new ShortLifeThread(childGroup, 8000);
		new ShortLifeThread(childGroup, 9000);
		new ShortLifeThread(childGroup, 10000);

		final Runnable showRunnable = new ThreadLevel(childGroup);
		new Thread(showRunnable).start();
	}
}
