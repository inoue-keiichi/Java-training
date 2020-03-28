package main.value.member;

import static main.Autowired.*;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import main.Autowired;
import main.InstanceField;
import main.Observer;
import main.PrintGenerator;
import main.StringUtils;
import main.value.ReflectionService;

public class MemberPanel extends JPanel implements ActionListener, ItemListener, Observer {

	final ReflectionService reflectionService = Autowired.reflectionService;

	final InstanceField instanceField = new InstanceField();
	final JTextField instanceText = instanceField.text;
	final JPanel typeCardPanel = new JPanel();
	final JComboBox<String> indexComboBox = new JComboBox<>();
	final CardLayout typeCardLayout = new CardLayout();
	final JButton setBtn = new JButton("Set");
	final JTabbedPane memberTab = new JTabbedPane();

	private static int i = 0;

	public MemberPanel() {
		// observer追加
		instanceField.addObserver(this);
		this.memberTab.addTab("field", Autowired.fieldPanel);
		this.memberTab.addTab("method", Autowired.methodPanel);
		// CardLayout定義
		this.typeCardPanel.setLayout(this.typeCardLayout);
		this.typeCardPanel.add(setBtn, "Object");
		this.typeCardPanel.add(indexComboBox, "Array");
		// レイアウト定義
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		// 配置
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Instance: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(instanceText, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		setBtn.addActionListener(this);
		indexComboBox.addItemListener(this);
		this.add(this.typeCardPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(memberTab, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String instanceName = instanceText.getText();
		//  instanceのkeyがなかったらエラー
		if (!StringUtils.macthRegex(instanceName)) {
			throw new IllegalArgumentException();
		}
		// インスタンスがなかったらエラー
		String key = instanceName.substring(2, instanceName.length() - 1);
		Object instance = reflectionService.getInstances().get(key);
		if (Objects.isNull(instance)) {
			throw new NullPointerException(key);
		}

		reflectionService.setNewInstance(instance);
		fieldPrintGenerator.execute(instance);
		methodPrintGenerator.execute(instance);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		String type = reflectionService.getInstanceType();
		this.typeCardLayout.show(this.typeCardPanel, type);

		if (type == "Object") {
			return;
		}
		// 配列のindexのcomboBoxに値を追加する
		String instanceName = instanceText.getText();
		//  instanceのkeyがなかったらエラー
		if (!StringUtils.macthRegex(instanceName)) {
			throw new IllegalArgumentException();
		}
		// インスタンスがなかったらエラー
		String key = instanceName.substring(2, instanceName.length() - 1);
		Object instance = reflectionService.getInstances().get(key);
		if (Objects.isNull(instance)) {
			throw new NullPointerException(key);
		}
		// Itemを入れ直す
		indexComboBox.removeAllItems();
		indexComboBox.addItem("");
		for (int i = 0; i < Array.getLength(instance); i++) {
			indexComboBox.addItem(Integer.toString(i));
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		try {
			final String item = (String) indexComboBox.getSelectedItem();
			if (item == "") {
				// TODO:field, methoodをクリアする命令を送る
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
			reflectionService.setNewInstance(instance);
			fieldPrintGenerator.execute(instance);
			methodPrintGenerator.execute(instance);
		} catch (IllegalArgumentException | NullPointerException e1) {
			errorHandler.execute(e1);
		}
	}
}
