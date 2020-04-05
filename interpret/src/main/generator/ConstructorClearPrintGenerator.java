package main.generator;

import main.di.AutowiredService;

public class ConstructorClearPrintGenerator extends PrintGenerator {

	public ConstructorClearPrintGenerator(AutowiredService service) {
		super(service);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void execute() {
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		return null;
	}

}
