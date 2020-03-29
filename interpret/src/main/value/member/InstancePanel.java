package main.value.member;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import main.InstanceListPanel;
import main.array.member.SetterPanel;
import main.array.type.ArrayTypeInputPanel;
import main.value.type.ObjectTypeInputPanel;

public class InstancePanel extends JPanel implements ItemListener {
	// 型、配列の切り替え
	final JComboBox<String> tabChangeComboBox = new JComboBox<>();
	// 型入力パネル
	final CardLayout typeInputCardLayout = new CardLayout();
	final JPanel typeInputCardPanel = new JPanel();
	// コンストラクターパネル
	final CardLayout instanceCreateCardLayout = new CardLayout();
	final JPanel instanceCreateCardPanel = new JPanel();

	public InstancePanel() {
		this.setLayout(new GridBagLayout());
		// typeInputCardのレイアウト定義
		typeInputCardPanel.setLayout(typeInputCardLayout);
		typeInputCardPanel.add(new ObjectTypeInputPanel(), "Type");
		typeInputCardPanel.add(new ArrayTypeInputPanel(), "Array");
		// instanceCreateCardのレイアウト定義
		instanceCreateCardPanel.setLayout(instanceCreateCardLayout);
		//instanceCreateCardPanel.add(Autowired.constructorPanel, "Type");
		instanceCreateCardPanel.add(new ConstructorPanel(), "Type");
		instanceCreateCardPanel.add(new SetterPanel(), "Array");
		// InterpretViewの配置決め
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		// 型と配列の切り替えプルダウン
		tabChangeComboBox.addItemListener(this);
		tabChangeComboBox.addItem("Type");
		tabChangeComboBox.addItem("Array");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		this.add(tabChangeComboBox, gbc);
		// 型入力パネル：型テキスト入力と設定ボタンがある
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTH;
		this.add(typeInputCardPanel, gbc);
		// インスタンスリスト
		final JPanel instanceListPanel = new InstanceListPanel();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(instanceListPanel, gbc);
		// コンストラクタパネル
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(instanceCreateCardPanel, gbc);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String cmd = (String) e.getItem();
		typeInputCardLayout.show(typeInputCardPanel, cmd);
		instanceCreateCardLayout.show(instanceCreateCardPanel, cmd);
	}
}
