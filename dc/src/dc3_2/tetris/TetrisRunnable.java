package dc3_2.tetris;

import java.util.Observable;

import dc3_2.tetris.clazz.Tetrimino;

public class TetrisRunnable extends Observable implements Runnable {
	private Tetrimino tetrimino;
	public WaitObject waitObject;

	public TetrisRunnable(final Tetrimino tetrimino) {
		this.tetrimino = tetrimino;
		this.waitObject = new WaitObject();
	}

	// このオブジェクトを使って待機状態に入る
	public synchronized void suspend() throws InterruptedException {
		this.setChanged();
		this.notifyObservers();
		this.wait();
	}

	// このオブジェクトを使って待機しているスレッドを全て起こす
	public synchronized void resume() {
		this.notifyAll();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(tetrimino.getSpeed());
				this.setChanged();
				this.notifyObservers();
				tetrimino.moveDown();
			} catch (final InterruptedException e) {
			}
		}
	}

	public void setTetrimino(final Tetrimino tetrimino) {
		this.tetrimino = tetrimino;
	}

	class WaitObject {

		// このオブジェクトを使って待機状態に入る
		public synchronized void suspend() throws InterruptedException {
			this.wait();
		}

		// このオブジェクトを使って待機しているスレッドを全て起こす
		public synchronized void resume() {
			this.notifyAll();
		}
	}

}
