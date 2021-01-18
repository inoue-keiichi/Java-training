package dc3_2.frame.game.tetris;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
//import java.util.Observable;

import dc3_2.frame.game.tetris.clazz.Tetrimino;

public class TetrisRunnable implements Runnable {
	private Tetrimino tetrimino;
	public WaitObject waitObject;
	private boolean runFlag;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public TetrisRunnable(final Tetrimino tetrimino) {
		this.tetrimino = tetrimino;
		this.waitObject = new WaitObject();
		this.runFlag = true;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	// このオブジェクトを使って待機状態に入る
//	public synchronized void suspend() throws InterruptedException {
//		this.setChanged();
//		this.notifyObservers();
//		this.wait();
//	}

	// このオブジェクトを使って待機しているスレッドを全て起こす
	public synchronized void resume() {
		this.notifyAll();
	}

	@Override
	public void run() {
		while (this.runFlag) {
			try {

//				this.setChanged();
//				this.notifyObservers();

				tetrimino.moveDown();
				this.pcs.firePropertyChange("tetrimino", null, null);
				Thread.sleep(tetrimino.getSpeed());

			} catch (final InterruptedException e) {
			}
		}
	}

	public void setTetrimino(final Tetrimino tetrimino) {
		this.tetrimino = tetrimino;
	}

	public void stop() {
		this.runFlag = false;
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
