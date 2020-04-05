package main.generator;

import java.lang.reflect.Array;

import main.clazz.Argument;
import main.di.AutowiredService;
import main.service.ReflectionService;

public class SetterPrintGenerator extends PrintGenerator {
	private final ReflectionService reflectionService;
	private int index;

	public SetterPrintGenerator(AutowiredService service) {
		super(service);
		reflectionService = service.reflectionService;
	}

	@Override
	public void execute() throws Throwable {
	}

	public void execute(final Object instance, final int index, final String element) throws Throwable {
		// ログ用
		this.index = index;
		int i = index;

		final Class<?> type = instance.getClass().getComponentType();
		final Argument arg = new Argument();
		arg.type = type;
		arg.value = element;
		final Object parsedElement = reflectionService.validateArgument(arg);
		Array.set(instance, i, parsedElement);
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		return "[Success] The element was set. (index: " + this.index + ")\n";
	}
}
