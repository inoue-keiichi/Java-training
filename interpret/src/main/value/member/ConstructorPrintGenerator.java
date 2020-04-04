package main.value.member;

import main.AutowiredService;
import main.PrintGenerator;
import main.value.ReflectionService;

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
