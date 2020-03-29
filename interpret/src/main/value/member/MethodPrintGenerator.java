package main.value.member;

import main.AutowiredService;
import main.PrintGenerator;
import main.value.ReflectionService;

public class MethodPrintGenerator extends PrintGenerator {
	private final ReflectionService reflectionService;

	public MethodPrintGenerator(AutowiredService service) {
		super(service);
		reflectionService = service.reflectionService;
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
		return "the methods were created.\n";
	}
}
