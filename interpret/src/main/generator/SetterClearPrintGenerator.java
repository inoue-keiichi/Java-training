package main.generator;

import main.di.AutowiredService;

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
