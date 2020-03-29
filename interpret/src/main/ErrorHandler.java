package main;

import java.lang.reflect.InvocationTargetException;

public class ErrorHandler extends PrintGenerator {
	Throwable exception;

	@Override
	public void execute() {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void execute(final Throwable e) {
		this.exception = e;
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Fail.\n");
		// InvocationTargetExceptionだったら中身の例外を見たい
		if (this.exception instanceof InvocationTargetException) {
			sb.append(this.exception.getCause());
		} else {
			sb.append(exception.toString());
		}
		sb.append("\n");
		return sb.toString();
	}
}
