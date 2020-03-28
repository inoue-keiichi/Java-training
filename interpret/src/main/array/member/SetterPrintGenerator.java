package main.array.member;

import java.lang.reflect.Array;

import main.Argument;
import main.Autowired;
import main.PrintGenerator;
import main.value.ReflectionService;

public class SetterPrintGenerator extends PrintGenerator {
	ReflectionService reflectionService = Autowired.reflectionService;

	private int index;

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
