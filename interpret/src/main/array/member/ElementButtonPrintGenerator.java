package main.array.member;

import main.PrintGenerator;

public class ElementButtonPrintGenerator extends PrintGenerator {
	private static ElementButtonPrintGenerator elementButtonPrintGenerator = new ElementButtonPrintGenerator();

	private ElementButtonPrintGenerator() {

	}

	@Override
	public void execute() throws Throwable {
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public static ElementButtonPrintGenerator getInstance() {
		return elementButtonPrintGenerator;
	}
}
