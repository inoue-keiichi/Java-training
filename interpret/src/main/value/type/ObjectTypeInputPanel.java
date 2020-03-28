package main.value.type;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Autowired;
import main.value.ReflectionService;

public class ObjectTypeInputPanel extends JPanel implements ActionListener {
	final ReflectionService reflectionService = Autowired.reflectionService;

	final JTextField typeText = new JTextField(30);
	final JButton setBtn = new JButton("Set");

	public ObjectTypeInputPanel() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		layout.setConstraints(typeText, constraints);
		this.add(typeText);

		constraints.gridx = 1;
		constraints.gridy = 0;
		layout.setConstraints(setBtn, constraints);
		this.add(setBtn);
		setBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			reflectionService.setClazz(typeText.getText());
		} catch (ClassNotFoundException e1) {
			Autowired.errorHandler.execute(e1);
		}
		// コンストラクタの項目を表示する
		Autowired.constructorPrintGenerator.execute();
	}
}
