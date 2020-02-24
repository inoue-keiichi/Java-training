package main.array.member;

import java.lang.reflect.Array;
import java.util.Objects;

import main.Argument;
import main.PrintGenerator;
import main.StringUtils;
import main.value.ReflectionService;

public class SetterPrintGenerator extends PrintGenerator {
	private static SetterPrintGenerator setterPrintGenerator = new SetterPrintGenerator();

	// ArrayReflectionService reflectionService =
	// ArrayReflectionService.getInstance();
	ReflectionService reflectionService = ReflectionService.getInstance();

	private String index;

	private SetterPrintGenerator() {

	}

	@Override
	public void execute() throws Throwable {
	}

	public void execute(final Object instance, final String index, final String element) throws Throwable {
		// ログ用
		this.index = index;
		int i = Integer.parseInt(index);

		// インスタンスを引き数に利用した場合
		if (StringUtils.macthRegex(element)) {
			String elementKey = StringUtils.getInstanceKey(element);
			Object elementInstance = reflectionService.getInstances().get(elementKey);
			if (Objects.isNull(elementInstance)) {
				throw new NullPointerException(elementKey);
			}
			Array.set(instance, i, elementInstance);
			this.notifyObservers();
			return;
		}
		// 文字列を引き数に利用した場合
		if (!(instance instanceof Object[])) {
			throw new IllegalArgumentException(instance.toString());
		}
		// final Object[] array = (Object[]) instance;
		// reflectionService.setElementArgTypes(array);
		final Class<?> type = instance.getClass().getComponentType();
		final Argument arg = new Argument();
		arg.type = type;
		arg.value = element;

		final Object parsedElement = reflectionService.parsePrimitive(arg);

		Array.set(instance, i, parsedElement);

		// this.index = index;
		this.notifyObservers();
	}

//	public Class<?> setElementArgTypes(Object[] instance) {
////		if (constructorsIndex == -1) {
////			return;
////		}
////		Class<?>[] types = constructors[constructorsIndex].getParameterTypes();
////		// 引き数なし
////		if (types.length < 1) {
////			this.constructorArgments = null;
////			return;
////		}
//		final Class<?> type = instance.getClass().getComponentType();
//		final int length = Array.getLength(instance);
//		final Argument[] args = new Argument[length];
//		for (Argument arg : args) {
//			arg.type = type;
//		}
//		this.elementArgments = args;
//
//		return type;
//	}

	@Override
	public String getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return "The element was set. (index: " + this.index + ")\n";
	}

	public static SetterPrintGenerator getInstance() {
		return setterPrintGenerator;
	}

}
