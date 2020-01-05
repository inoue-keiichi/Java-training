package ch14.ex06;

public class TimeThread {
	private int time = 0;
	private final Message message;

	public TimeThread(Message message) {
		this.message = message;
		Runnable service = new Runnable() {
			@Override
			public void run() {
				for (;;) {
					try {
						time++;
						Thread.sleep(1000);
						message.print(time);
					} catch (InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(service).start();
	}
}
