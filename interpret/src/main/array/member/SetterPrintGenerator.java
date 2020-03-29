package main.array.member;

import java.lang.reflect.Array;

import main.Argument;
import main.AutowiredService;
import main.PrintGenerator;
import main.value.ReflectionService;

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

		// this.index = index;
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return "The element was set. (index: " + this.index + ")\n";
	}
}
