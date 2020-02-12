package main.array.type;

import java.lang.reflect.Array;

import main.PrintGenerator;
import main.array.ArrayReflectionService;

public class ArrayCreatePrintGenerator extends PrintGenerator {
	private static final ArrayCreatePrintGenerator arrayCreatePrintGenerator = new ArrayCreatePrintGenerator();

	private final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();

	@Override
	public void execute() throws ClassNotFoundException {
		final String referenceName = reflectionService.getReferenceName();
		final int arraySize = reflectionService.getArraySize();
		final Class<?> clazz = Class.forName(referenceName);
		final Object arrayInstance = Array.newInstance(clazz, arraySize);
		reflectionService.setArrayNewInstance(arrayInstance);
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		return "Success!\nAn array was created whose type was " + reflectionService.getReferenceName() + ".\n";
	}

	public static ArrayCreatePrintGenerator getInstance() {
		return arrayCreatePrintGenerator;
	}

}
