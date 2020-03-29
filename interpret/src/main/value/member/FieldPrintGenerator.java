package main.value.member;

import main.AutowiredService;
import main.PrintGenerator;

public class FieldPrintGenerator extends PrintGenerator {
	public FieldPrintGenerator(AutowiredService service) {
		super(service);
	}

	@Override
	public void execute() {
	}

	//TODO: delete it soon.
	public void execute(final Object instance) {
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		return "the fields were created.\n";
	}
}
