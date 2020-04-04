package main.array.member;

import main.AutowiredService;
import main.PrintGenerator;

public class SetterClearPrintGenerator extends PrintGenerator {

	public SetterClearPrintGenerator(AutowiredService service) {
		super(service);
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
