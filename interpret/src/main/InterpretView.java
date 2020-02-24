package main;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.WEST;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.array.type.ArrayTypeInputPanel;
import main.value.type.ObjectTypeInputPanel;
import main.array.member.ArrayTypeMemberTab;
import main.value.ReflectionService;
import main.value.member.ConstructorPanel;
import main.value.member.InstancePanel;
import main.value.member.MemberPanel;
import main.value.member.ObjectMemberTab;

public class InterpretView extends JFrame implements Observer, Runnable, ItemListener {
	private static final InterpretView interpretView = new InterpretView();

	private final ReflectionService reflectionService = ReflectionService.getInstance();

	private final Thread thread = new Thread(this);
//	// 型、配列の切り替え
//	final JComboBox<String> tabChangeComboBox = new JComboBox<>();
//	// 型入力パネル
//	final CardLayout typeInputCardLayout = new CardLayout();
//	final JPanel typeInputCardPanel = new JPanel();
	// コンストラクターパネル
	ConstructorPanel constructorPanel = Autowired.constructorPanel;
	// メンバータブパネル
	final CardLayout memberTabCardLayout = new CardLayout();
	final JPanel memberTabCardPanel = new JPanel();
	// ログテキストエリア
	final JTextArea logTextArea = LogTextArea.getInstance();

	public InterpretView() {
		final JPanel pane = new JPanel(new GridBagLayout());
		this.setContentPane(pane);
//		// typeInputCardのレイアウト定義
//		typeInputCardPanel.setLayout(typeInputCardLayout);
//		typeInputCardPanel.add(ObjectTypeInputPanel.getInstance(), "Type");
//		typeInputCardPanel.add(new ArrayTypeInputPanel(), "Array");
		// memberTabCardのレイアウト定義
		memberTabCardPanel.setLayout(memberTabCardLayout);
		// memberTabCardPanel.add(new JPanel(), "Null");
		memberTabCardPanel.add(Autowired.objectMemberTab, "Type");
		memberTabCardPanel.add(ArrayTypeMemberTab.getInstance(), "Array");
		// InterpretViewの配置決め
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
//		// 型と配列の切り替えプルダウン
//		tabChangeComboBox.addItemListener(this);
//		tabChangeComboBox.addItem("Type");
//		tabChangeComboBox.addItem("Array");
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.anchor = GridBagConstraints.EAST;
//		pane.add(tabChangeComboBox, gbc);
//		// 型入力パネル：型テキスト入力と設定ボタンがある
//		gbc.gridx = 1;
//		gbc.gridy = 0;
//		gbc.gridwidth = 3;
//		gbc.weightx = 1;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.anchor = GridBagConstraints.WEST;
//		pane.add(typeInputCardPanel, gbc);

//		// インスタンスリスト
//		final JPanel instanceListPanel = InstanceListPanel.getInstance();
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		gbc.gridwidth = 1;
//		pane.add(instanceListPanel, gbc);
//		// コンストラクタパネル
//		// final JPanel constractorPanel = constructorPanel;
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//		gbc.gridwidth = 2;
//		gbc.fill = GridBagConstraints.BOTH;
//		gbc.anchor = GridBagConstraints.CENTER;
//		pane.add(constructorPanel, gbc);

		// インスタンスパネル
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		pane.add(new InstancePanel(), gbc);
		// メンバタブ
		final JPanel tabbedPane = memberTabCardPanel;
		gbc.gridx = 1;
		gbc.gridy = 0;
		// gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		// pane.add(tabbedPane, gbc);
		pane.add(new MemberPanel(), gbc);
		// ログパネル
		this.logTextArea.setEditable(false);
		final JScrollPane scrollpane = new JScrollPane(logTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		pane.add(scrollpane, gbc);

		addWindowListener(new InterpretWindowAdapter());
		this.setVisible(true);
		this.setMinimumSize(new Dimension(600, 450));
	}

	public static final InterpretView getInstance() {
		return interpretView;
	}

	@Override
	public void run() {
		while (true) {

		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String cmd = (String) e.getItem();
//		typeInputCardLayout.show(typeInputCardPanel, cmd);
//		// クラスが設定されてなかったら表示しない
//		if (reflectionService.getClazzName() == null) {
//			memberTabCardLayout.show(memberTabCardPanel, "Null");
//			return;
//		} else {
//			memberTabCardLayout.show(memberTabCardPanel, cmd);
//		}
	}

	@Override
	public void update(PrintGenerator printGenerator) {
//		final String item = this.tabChangeComboBox.getSelectedItem().toString();
//		memberTabCardLayout.show(memberTabCardPanel, item);

	}
}