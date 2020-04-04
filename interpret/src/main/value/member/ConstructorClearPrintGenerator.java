package main.value.member;

import main.AutowiredService;
import main.PrintGenerator;

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
