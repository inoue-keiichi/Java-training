package main.value.member;

import java.lang.reflect.*;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.JComboBox;

import main.Autowired;
import main.ItemComparator;
import main.PrintGenerator;
import main.StringUtils;
import main.value.ReflectionService;
import static main.StringUtils.*;

public class MethodPrintGenerator extends PrintGenerator {
	// private static final MethodPrintGenerator methodPrintGenerator = new
	// MethodPrintGenerator();

	private final ReflectionService reflectionService = ReflectionService.getInstance();
	private final MethodPanel memberPanel = Autowired.methodPanel;

	@Override
	public void execute() {
	}

	public void execute(final Object instance) {
		// インスタンスからMethodを取得してプルダウンに表示する
		final JComboBox<String> methodComboBox = memberPanel.getMethodCoomboBox();
		// Method[] methods = instance.getClass().getDeclaredMethods();
		List<Method> methodList = Arrays.asList(instance.getClass().getDeclaredMethods());
		methodList = new ArrayList<Method>(methodList);
		// superClassのmethodがあれば追加する
		Class<?> clazz = instance.getClass();
		while (Objects.nonNull(clazz) && !Objects.equals(clazz.getName(), Object.class.getName())) {
			clazz = clazz.getSuperclass();
			Method[] superMethods = clazz.getDeclaredMethods();
			List<Method> superMethodList = Arrays.asList(superMethods);
			methodList.addAll(superMethodList);
		}
		methodList.sort(new ItemComparator());
		// methodを保存
		Method[] methods = methodList.toArray(new Method[methodList.size()]);
		reflectionService.setMethods(methods);
		// 元々あった選択肢を削除
		if (methodComboBox.getItemCount() != 0) {
			methodComboBox.removeAllItems();
		}
		for (Method method : methods) {
			methodComboBox.addItem(getNameAndParameter(method));
		}
		this.notifyObservers();
	}

//	public static MethodPrintGenerator getInstance() {
//		return methodPrintGenerator;
//	}

	@Override
	public String getLog() {
		if (memberPanel.getMethodCoomboBox().getItemCount() < 1) {
			return "";
		}
		return "the methods were created.\n";
	}
}
