package main.value.member;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import main.AutowiredGenerator;
import main.AutowiredService;
import main.InstanceListPanel;
import main.View;
import main.array.member.SetterPanel;
import main.array.type.ArrayTypeInputPanel;
import main.value.type.ObjectTypeInputPanel;

public class InstancePanel extends View implements ItemListener {
	// 型、配列の切り替え
	final JComboBox<String> tabChangeComboBox = new JComboBox<>();
	// 型入力パネル
	final CardLayout typeInputCardLayout = new CardLayout();
	final JPanel typeInputCardPanel = new JPanel();
	// コンストラクターパネル
	final CardLayout instanceCreateCardLayout = new CardLayout();
	final JPanel instanceCreateCardPanel = new JPanel();
	final ObjectTypeInputPanel objectTypeInputPanel;
	final ArrayTypeInputPanel arrayTypeInputPanel;
	final ConstructorPanel constructorPanel;
	final SetterPanel setterPanel;

	public InstancePanel(final AutowiredGenerator generator, final AutowiredService service) {
		super(new JPanel(), generator, service);

		// 構成要素
		this.objectTypeInputPanel = new ObjectTypeInputPanel(this.generator, this.service);
		this.arrayTypeInputPanel = new ArrayTypeInputPanel(this.generator, this.service);
		this.constructorPanel = new ConstructorPanel(this.generator, this.service);
		this.setterPanel = new SetterPanel(this.generator, this.service);

		this.view.setLayout(new GridBagLayout());
		// typeInputCardのレイアウト定義
		typeInputCardPanel.setLayout(typeInputCardLayout);
		typeInputCardPanel.add(this.objectTypeInputPanel.view, "Object");
		typeInputCardPanel.add(this.arrayTypeInputPanel.view, "Array");
		// instanceCreateCardのレイアウト定義
		instanceCreateCardPanel.setLayout(instanceCreateCardLayout);
		instanceCreateCardPanel.add(this.constructorPanel.view, "Object");
		instanceCreateCardPanel.add(this.setterPanel.view, "Array");
		// InterpretViewの配置決め
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		// 型と配列の切り替えプルダウン
		tabChangeComboBox.addItemListener(this);
		tabChangeComboBox.addItem("Object");
		tabChangeComboBox.addItem("Array");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.view.add(tabChangeComboBox, gbc);
		// 型入力パネル：型テキスト入力と設定ボタンがある
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTH;
		this.view.add(typeInputCardPanel, gbc);
		// インスタンスリスト
		final JPanel instanceListPanel = (JPanel) new InstanceListPanel(this.generator, this.service).view;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		this.view.add(instanceListPanel, gbc);
		// コンストラクタパネル
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.view.add(instanceCreateCardPanel, gbc);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String cmd = (String) e.getItem();
		typeInputCardLayout.show(typeInputCardPanel, cmd);
		instanceCreateCardLayout.show(instanceCreateCardPanel, cmd);
	}
}
