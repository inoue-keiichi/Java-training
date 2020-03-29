package main.value.member;

import main.Autowired;
import main.PrintGenerator;
import main.value.ReflectionService;

public class MethodPrintGenerator extends PrintGenerator {
	private final ReflectionService reflectionService = Autowired.reflectionService;
	//private final MethodPanel memberPanel = Autowired.methodPanel;

	@Override
	public void execute() {
	}

	public void execute(final Object instance) {
		//		// インスタンスからMethodを取得してプルダウンに表示する
		//		final JComboBox<String> methodComboBox = memberPanel.getMethodCoomboBox();
		//		List<Method> methodList = Arrays.asList(instance.getClass().getDeclaredMethods());
		//		methodList = new ArrayList<Method>(methodList);
		//		// superClassのmethodがあれば追加する
		//		Class<?> clazz = instance.getClass();
		//		while (Objects.nonNull(clazz) && !Objects.equals(clazz.getName(), Object.class.getName())) {
		//			clazz = clazz.getSuperclass();
		//			Method[] superMethods = clazz.getDeclaredMethods();
		//			List<Method> superMethodList = Arrays.asList(superMethods);
		//			methodList.addAll(superMethodList);
		//		}
		//		methodList.sort(new ItemComparator());
		//		// methodを保存
		//		Method[] methods = methodList.toArray(new Method[methodList.size()]);
		//		reflectionService.setMethods(methods);
		//		// 元々あった選択肢を削除
		//		if (methodComboBox.getItemCount() != 0) {
		//			methodComboBox.removeAllItems();
		//		}
		//		for (Method method : methods) {
		//			methodComboBox.addItem(getNameAndParameter(method));
		//		}
		this.notifyObservers();
	}

	@Override
	public String getLog() {
		//		if (memberPanel.getMethodCoomboBox().getItemCount() < 1) {
		//			return "";
		//		}
		return "the methods were created.\n";
	}
}
