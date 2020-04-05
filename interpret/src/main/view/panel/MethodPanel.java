package main.view.panel;

import static java.awt.GridBagConstraints.*;
import static main.utils.StringUtils.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.clazz.Argument;
import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.ErrorHandler;
import main.generator.MethodExecutePrintGenerator;
import main.generator.PrintGenerator;
import main.service.MemberService;
import main.service.ReflectionService;
import main.view.Observer;
import main.view.View;
import main.view.button.ObserverButton;
import main.view.field.ArgText;

public class MethodPanel extends View implements Observer, ItemListener, ActionListener {
	private final ReflectionService reflectionService;
	private final MemberService memberService;
	private final MethodExecutePrintGenerator methodExecutePrintGenerator;
	private final ErrorHandler errorHandler;

	private final JComboBox<String> methodComboBox;
	private final JLabel argsLabel;
	private final JPanel argsPanel;
	private final ObserverButton executeBtn;
	private final GridBagConstraints gbc;
	private final GridBagLayout layout;

	public MethodPanel(final AutowiredGenerator generator, final AutowiredService service) {
		super(new JPanel(), generator, service);
		this.reflectionService = this.service.reflectionService;
		this.memberService = this.service.memberService;
		this.methodExecutePrintGenerator = this.generator.methodExecutePrintGenerator;
		this.errorHandler = this.generator.errorHandler;
		this.generator.methodPrintGenerator.addObserver(this);

		//構成要素
		this.methodComboBox = new JComboBox<>();
		this.argsLabel = new JLabel("Argument: ");
		this.argsPanel = new JPanel();
		this.executeBtn = new ObserverButton("Execute");
		this.generator.methodPrintGenerator.addObserver(executeBtn);

		this.gbc = new GridBagConstraints();
		this.layout = new GridBagLayout();
		this.view.setLayout(layout);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Method: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.view.add(methodComboBox, gbc);
		methodComboBox.addItemListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.view.add(executeBtn.btn, gbc);
		executeBtn.btn.addActionListener(this);
	}

	public void createArgumentPanel(final Argument[] args) {
		this.argsPanel.removeAll();
		this.view.revalidate();
		if (args == null) {
			this.view.remove(this.argsLabel);
			this.view.repaint();
			return;
		}
		for (Argument arg : args) {
			this.argsPanel.add(new ArgText(this.generator.errorHandler).text);
		}
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.anchor = WEST;
		this.view.add(this.argsLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.anchor = EAST;
		this.view.add(this.argsPanel, gbc);
		this.argsPanel.revalidate();
	}

	//	public JComboBox<String> getMethodCoomboBox() {
	//		return methodComboBox;
	//	}

	public void clearMethod() {
		this.methodComboBox.removeAllItems();
		this.executeBtn.btn.setEnabled(false);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		final Object instance = memberService.getInstance();

		// インスタンスからMethodを取得してプルダウンに表示する
		final JComboBox<String> methodComboBox = this.methodComboBox;
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
		//methodList.sort(new ItemComparator());
		Method[] methods = methodList.toArray(new Method[methodList.size()]);
		// 元々あった選択肢を削除
		if (methodComboBox.getItemCount() != 0) {
			methodComboBox.removeAllItems();
		}
		// MethodのMapを保存する
		Map<String, Method> methodMap = new HashMap<>();
		for (Method method : methods) {
			methodMap.put(getNameAndParameter(method), method);
		}
		reflectionService.setMethodMap(methodMap);
		// ソートしてプルダウンに追加する
		final List<String> methodKey = new ArrayList<>(methodMap.keySet());
		Collections.sort(methodKey);
		for (String key : methodKey) {
			methodComboBox.addItem(key);
		}

		//		for (Method method : methods) {
		//			methodComboBox.addItem(getNameAndParameter(method));
		//		}
	}

	public JComboBox<String> getMethodComboBox() {
		return methodComboBox;
	}

	public JPanel getArgsPanel() {
		return argsPanel;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		reflectionService.setMethodArgTypes((String) methodComboBox.getSelectedItem());
		createArgumentPanel(reflectionService.getMethodArgments());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			memberService.setMethodPanel(this);
			methodExecutePrintGenerator.execute();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException
				| SecurityException e1) {
			errorHandler.execute(e1);
		}
	}
}
