package main.array.type;

import java.lang.reflect.Array;

import main.InstanceListPanel;
import main.PrintGenerator;
import main.value.ReflectionService;

public class ArrayCreatePrintGenerator extends PrintGenerator {
	private static final ArrayCreatePrintGenerator arrayCreatePrintGenerator = new ArrayCreatePrintGenerator();

	// private final ArrayReflectionService reflectionService =
	// ArrayReflectionService.getInstance();
	private final ReflectionService reflectionService = ReflectionService.getInstance();

	@Override
	public void execute() throws ClassNotFoundException {
		final String referenceName = reflectionService.getReferenceName();
		final int arraySize = reflectionService.getArraySize();
		final Class<?> clazz = Class.forName(referenceName);
		final Object[] arrayInstance = (Object[]) Array.newInstance(clazz, arraySize);
		// reflectionService.setArrayNewInstance(arrayInstance);

		// 作ったインスタンスは再利用できるようにする
		String key = arrayInstance.getClass().getSimpleName();
		// 同じキーがあれば番号を付加する
		key = arrangeKey(key);
		reflectionService.getInstances().put(key, arrayInstance);
		InstanceListPanel.getInstance().addList(key);
		this.notifyObservers();
	}

	/**
	 * インスタンスに紐づく適切なキーを返します。
	 *
	 *
	 * @param key
	 * @return
	 */
	private String arrangeKey(final String key) {
		if (!reflectionService.getInstances().containsKey(key)) {
			return key;
		}
		int i = 1;
		String changedKey = key.substring(0, key.indexOf("[")) + "_" + i + "[]";
		// keyが被らないようにする
		while (reflectionService.getInstances().containsKey(changedKey)) {
			i++;
			changedKey = key.substring(0, key.indexOf("[")) + "_" + i + "[]";
		}
		return changedKey;
	}

	@Override
	public String getLog() {
		return "Success!\nAn array was created whose type was " + reflectionService.getReferenceName() + ".\n";
	}

	public static ArrayCreatePrintGenerator getInstance() {
		return arrayCreatePrintGenerator;
	}

}
