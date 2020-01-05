package ch14.ex09;

public class ShortLifeThread {
	public ShortLifeThread(final ThreadGroup group, final int time) {
		Runnable service = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		};
		new Thread(group, service).start();
	}
}
