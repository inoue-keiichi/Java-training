package main.view.panel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.ErrorHandler;
import main.generator.FieldPrintGenerator;
import main.generator.MethodPrintGenerator;
import main.generator.PrintGenerator;
import main.service.MemberService;
import main.service.ReflectionService;
import main.utils.StringUtils;
import main.view.Observer;
import main.view.View;
import main.view.field.InstanceField;

public class MemberPanel extends View implements ItemListener, Observer {

	final ReflectionService reflectionService;
	final MemberService memberService;
	final FieldPrintGenerator fieldPrintGenerator;
	final MethodPrintGenerator methodPrintGenerator;
	final ErrorHandler errorHandler;

	final InstanceField instanceField;
	final JTextField instanceText;
	final JPanel typeCardPanel;
	final JComboBox<String> indexComboBox;
	final CardLayout typeCardLayout;
	final JTabbedPane memberTab;
	final FieldPanel fieldPanel;
	final MethodPanel methodPanel;

	private static int i = 0;

	public MemberPanel(final AutowiredGenerator generator, final AutowiredService service) {
		super(new JPanel(), generator, service);
		this.reflectionService = this.service.reflectionService;
		this.memberService = this.service.memberService;
		this.fieldPrintGenerator = this.generator.fieldPrintGenerator;
		this.methodPrintGenerator = this.generator.methodPrintGenerator;
		this.errorHandler = this.generator.errorHandler;

		this.instanceField = new InstanceField(this.generator.errorHandler, this.service);
		this.instanceText = this.instanceField.text;
		this.typeCardPanel = new JPanel();
		this.indexComboBox = new JComboBox<>();
		this.typeCardLayout = new CardLayout();
		this.memberTab = new JTabbedPane();
		this.memberTab.setPreferredSize(new Dimension(550, 200));
		this.fieldPanel = new FieldPanel(this.generator, this.service);
		this.methodPanel = new MethodPanel(this.generator, this.service);

		// observer追加
		instanceField.addObserver(this);
		this.memberTab.addTab("field", fieldPanel.view);
		this.memberTab.addTab("method", methodPanel.view);
		// CardLayout定義
		this.typeCardPanel.setLayout(this.typeCardLayout);
		this.typeCardPanel.add(new JLabel(), "Object");
		this.typeCardPanel.add(indexComboBox, "Array");
		// レイアウト定義
		this.view.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 20, 0, 0);
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		// 配置
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.view.add(new JLabel("Instance: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		instanceText.setEditable(false);
		this.view.add(instanceText, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		indexComboBox.addItemListener(this);
		this.view.add(this.typeCardPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		this.view.add(memberTab, gbc);
	}

	private void printMemberForObject(final Object instance) {
		memberService.setInstance(instance);
		fieldPrintGenerator.execute(instance);
		methodPrintGenerator.execute(instance);
	}

	private void printMemberForArray(final Object instance) {
		// Itemを入れ直す
		indexComboBox.removeAllItems();
		indexComboBox.addItem("");
		for (int i = 0; i < Array.getLength(instance); i++) {
			indexComboBox.addItem(Integer.toString(i));
		}
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		String type = reflectionService.getInstanceType();
		this.typeCardLayout.show(this.typeCardPanel, type);

		try {
			String instanceName = instanceText.getText();
			//  instanceのkeyがなかったらエラー
			if (!StringUtils.macthRegex(instanceName)) {
				throw new IllegalArgumentException();
			}
			// インスタンスがなかったらエラー
			String key = instanceName.substring(2, instanceName.length() - 1);
			Object instance = reflectionService.getInstances().get(key);
			if (Objects.isNull(instance)) {
				throw new NullPointerException("\"" + key + "\" is not found in Instance List.");
			}

			if (type == "Object") { // オブジェクトのフィールドとメソッドを表示
				printMemberForObject(instance);
			} else { // 配列の要素を選ぶプルダウンを表示
				printMemberForArray(instance);
			}
		} catch (Exception e1) {
			this.fieldPanel.clearField();
			this.methodPanel.clearMethod();
			errorHandler.execute(e1);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		try {
			final String item = (String) indexComboBox.getSelectedItem();
			if (item == "") {
				this.fieldPanel.clearField();
				this.methodPanel.clearMethod();
				return;
			}
			//  instanceのkeyがなかったらエラー
			String instanceName = instanceText.getText();
			if (!StringUtils.macthRegex(instanceName)) {
				throw new IllegalArgumentException();
			}
			// インスタンスがなかったらエラー
			String key = instanceName.substring(2, instanceName.length() - 1);
			Object array = reflectionService.getInstances().get(key);
			if (Objects.isNull(array)) {
				throw new NullPointerException(key);
			}
			int index = Integer.parseInt(item);
			Object instance = Array.get(array, index);
			if (Objects.isNull(instance)) {
				throw new NullPointerException(key);
			}
			memberService.setInstance(instance);
			fieldPrintGenerator.execute(instance);
			methodPrintGenerator.execute(instance);
		} catch (Exception e1) {
			this.fieldPanel.clearField();
			this.methodPanel.clearMethod();
			errorHandler.execute(e1);
		}
	}
}
