package fxjunit;

import java.util.concurrent.CountDownLatch;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import javafx.application.Platform;

public class JavaFxJUnit4ClassRunner extends BlockJUnit4ClassRunner {

	public JavaFxJUnit4ClassRunner(final Class<?> klass) throws InitializationError {
		super(klass);
		JavaFxJUnit4Application.startJavaFx();
	}

	/**
	 * 各テストケースを実行する
	 */
	@Override
	protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
		// テスト実行スレッドの管理オブジェクトを作成する。
		final CountDownLatch latch = new CountDownLatch(1);

		// JavaFX管理上のスレッドでテストケースを実行する。
		Platform.runLater(() -> {
			// テストケースを実行する
			super.runChild(method, notifier);
			// スレッド管理オブジェクトをデクリメントする
			latch.countDown();
		});

		// テストケースが終わるまで待つ。
		try {
			latch.await();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
