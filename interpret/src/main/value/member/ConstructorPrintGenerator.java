package main.value.member;

import main.Autowired;
import main.PrintGenerator;
import main.value.ReflectionService;

public class ConstructorPrintGenerator extends PrintGenerator {
	private final ReflectionService reflectionService = Autowired.reflectionService;

	@Override
	public void execute() {
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		return "Success!\nYou can select the constructor of " + reflectionService.getClazzName() + ".\n";
	}
}
