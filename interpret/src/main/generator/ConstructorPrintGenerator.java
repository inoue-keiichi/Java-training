package main.generator;

import main.di.AutowiredService;
import main.service.ReflectionService;

public class ConstructorPrintGenerator extends PrintGenerator {
	private final ReflectionService reflectionService;

	public ConstructorPrintGenerator(AutowiredService service) {
		super(service);
		reflectionService = service.reflectionService;
	}

	@Override
	public void execute() {
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		return "[Success] the constructors of " + reflectionService.getClazzName() + " were created.\n";
	}
}
