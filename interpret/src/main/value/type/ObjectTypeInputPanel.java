package main.value.type;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Autowired;
import main.ErrorHandler;
import main.Observer;
import main.PrintGenerator;
import main.value.ReflectionService;
import main.value.member.ConstructorPanel;
import main.value.member.ConstructorPrintGenerator;

public class ObjectTypeInputPanel extends JPanel implements ActionListener {
	// private static final ObjectTypeInputPanel objectTypeInputPanel = new
	// ObjectTypeInputPanel();

	final ReflectionService reflectionService = ReflectionService.getInstance();
	// final ConstructorPrintGenerator constructorPrintGenerator =
	// Autowired.constructorPrintGenerator;
	// final MemberPrintGenerator memberPrintGenerator =
	// MemberPrintGenerator.getInstance();
	final ErrorHandler errorHandler = ErrorHandler.getInstance();

	final JTextField typeText = new JTextField(30);
	final JButton setBtn = new JButton("Set");
	final ConstructorPanel constructorPanel;

	public ObjectTypeInputPanel() {
		constructorPanel = Autowired.constructorPanel;

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		// constraints.anchor = GridBagConstraints.NORTH;
		layout.setConstraints(typeText, constraints);
		this.add(typeText);

		constraints.gridx = 1;
		constraints.gridy = 0;
		// onstraints.anchor = GridBagConstraints.NORTH;
		layout.setConstraints(setBtn, constraints);
		this.add(setBtn);
		setBtn.addActionListener(this);

//		constraints.gridx = 0;
//		constraints.gridy = 1;
//		constraints.gridwidth = 2;
//		constraints.anchor = GridBagConstraints.NORTH;
//		layout.setConstraints(constructorPanel, constraints);
//		this.add(constructorPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			reflectionService.setClazz(typeText.getText());
		} catch (ClassNotFoundException e1) {
			ErrorHandler.getInstance().execute(e1);
		}
		// コンストラクタの項目を表示する
		Autowired.constructorPrintGenerator.execute();
		// メンバータブを表示する
		try {
			// memberPrintGenerator.execute();
		} catch (Throwable e1) {
			errorHandler.execute(e1);
		}
	}

//	public static ObjectTypeInputPanel getInstance() {
//		return objectTypeInputPanel;
//	}
}
