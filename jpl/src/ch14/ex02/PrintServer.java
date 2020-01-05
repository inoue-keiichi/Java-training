package ch14.ex02;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.*;

public class PrintServer implements Runnable {
	private final PrintQueue requests = new PrintQueue();
	private final String THREAD_NAME = "printServer";

	public PrintServer() {
		Thread thread = new Thread(this);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
		Thread.currentThread().setName(THREAD_NAME);
	}

	public void print(PrintJob job) {
		requests.add(job);
	}

	@Override
	public void run() {
		if (Thread.currentThread().getName() == THREAD_NAME) {
			throw new RuntimeException("別のスレッドからrunメソッドを実行しないでください");
		}
		for (;;)
			realPrint(requests.remove());
	}

	private void realPrint(PrintJob job) {

	}

}

class ExceptionHandler implements UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		e.printStackTrace();
	}
}

class PrintQueue implements Queue<PrintJob> {

	@Override
	public int size() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Iterator<PrintJob> iterator() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends PrintJob> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void clear() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean add(PrintJob e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean offer(PrintJob e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public PrintJob remove() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public PrintJob poll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public PrintJob element() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public PrintJob peek() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}

class PrintJob {

}
