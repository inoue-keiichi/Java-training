package main.value.member;

import static java.awt.GridBagConstraints.*;
import static main.StringUtils.*;

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
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.ArgText;
import main.Argument;
import main.Autowired;
import main.ItemComparator;
import main.Observer;
import main.PrintGenerator;
import main.value.ReflectionService;

public class MethodPanel extends JPanel implements Observer, ItemListener, ActionListener {
	// private static final MethodPanel methodPanel = new MethodPanel();

	private final ReflectionService reflectionService = Autowired.reflectionService;

	private final JComboBox<String> methodComboBox = new JComboBox<>();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JButton executeBtn = new JButton("Execute");
	private final GridBagConstraints gbc = new GridBagConstraints();
	private final GridBagLayout layout = new GridBagLayout();

	public MethodPanel() {
		Autowired.methodPrintGenerator.addObserver(this);

		this.setLayout(layout);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.add(new JLabel("Method: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.add(methodComboBox, gbc);
		methodComboBox.addItemListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(executeBtn, gbc);
		executeBtn.addActionListener(this);
	}

	public void createArgumentPanel(final Argument[] args) {
		this.argsPanel.removeAll();
		this.revalidate();
		if (args == null) {
			this.remove(this.argsLabel);
			this.repaint();
			return;
		}
		for (Argument arg : args) {
			// this.argsPanel.add(new JTextField(5));
			this.argsPanel.add(new ArgText().text);
		}
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.anchor = WEST;
		this.add(this.argsLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.anchor = EAST;
		this.add(this.argsPanel, gbc);
		this.argsPanel.revalidate();
	}

	//	public JComboBox<String> getMethodCoomboBox() {
	//		return methodComboBox;
	//	}

	@Override
	public void update(PrintGenerator printGenerator) {
		final Object instance = Autowired.memberService.getInstance();

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
	}

	public JComboBox<String> getMethodComboBox() {
		return methodComboBox;
	}

	public JPanel getArgsPanel() {
		return argsPanel;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		reflectionService.setMethodArgTypes(methodComboBox.getSelectedIndex());
		createArgumentPanel(reflectionService.getMethodArgments());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Autowired.memberService.setMethodPanel(this);
			Autowired.methodExecutePrintGenerator.execute();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException
				| SecurityException e1) {
			Autowired.errorHandler.execute(e1);
		}
	}
}
