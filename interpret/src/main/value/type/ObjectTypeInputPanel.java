package main.value.type;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.AutowiredGenerator;
import main.AutowiredService;
import main.ErrorHandler;
import main.View;
import main.value.ReflectionService;
import main.value.member.ConstructorPrintGenerator;

public class ObjectTypeInputPanel extends View implements ActionListener {
	final ReflectionService reflectionService;
	final ConstructorPrintGenerator constructorPrintGenerator;
	final ErrorHandler errorHandler;

	final JTextField typeText = new JTextField(30);
	final JButton setBtn = new JButton("Set");

	public ObjectTypeInputPanel(final AutowiredGenerator generator, final AutowiredService service) {
		super(new JPanel(), generator, service);
		this.reflectionService = this.service.reflectionService;
		this.constructorPrintGenerator = this.generator.constructorPrintGenerator;
		this.errorHandler = this.generator.errorHandler;

		GridBagLayout layout = new GridBagLayout();
		this.view.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		layout.setConstraints(typeText, constraints);
		this.view.add(typeText);

		constraints.gridx = 1;
		constraints.gridy = 0;
		layout.setConstraints(setBtn, constraints);
		this.view.add(setBtn);
		setBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			reflectionService.setClazz(typeText.getText());
		} catch (ClassNotFoundException e1) {
			this.errorHandler.execute(e1);
		}
		// コンストラクタの項目を表示する
		this.constructorPrintGenerator.execute();
	}
}
