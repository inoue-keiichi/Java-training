package main.value.member;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.rmi.NoSuchObjectException;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import main.ArgText;
import main.Autowired;

import static main.Autowired.*;
import main.StringUtils;
import main.value.ReflectionService;

public class MemberPanel extends JPanel implements ActionListener {

	final ReflectionService reflectionService = ReflectionService.getInstance();

	final JTextField instanceText = new ArgText(10);
	final JTextField sizeText = new JTextField(5);
	final JButton setBtn = new JButton("Set");
	final JTabbedPane memberTab = new JTabbedPane();

	public MemberPanel() {
		// this.addTab("constructor", ConstructorPanel.getInstance());
		// this.memberTab.addTab("setter", Autowired.setterPanel);
		this.memberTab.addTab("field", Autowired.fieldPanel);
		this.memberTab.addTab("method", Autowired.methodPanel);

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
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(sizeText, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		setBtn.addActionListener(this);
		this.add(setBtn, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
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

		// Arrayだったら要素を取り出す
		if (instance instanceof Object[]) {
			// System.out.println("This type is Object[].");
			int i = Integer.parseInt(sizeText.getText());
			instance = Array.get(instance, i);
		}

		reflectionService.setNewInstance(instance);
		fieldPrintGenerator.execute(instance);
		methodPrintGenerator.execute(instance);
		// reflectionService.setNewInstance();
	}
}
