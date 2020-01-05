package ch14.ex06;

public class MessageThread {
	private final Message message;

	MessageThread(Message message) {
		this.message = message;
		Runnable service = new Runnable() {
			@Override
			public void run() {
				for (;;) {
					try {
						message.suspend();
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
