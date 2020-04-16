package main.view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.ArrayCreatePrintGenerator;
import main.generator.ErrorHandler;
import main.service.ReflectionService;
import main.view.View;

public class ArrayTypeInputPanel extends View implements ActionListener {
	final ReflectionService reflectionService;
	final ArrayCreatePrintGenerator arrayCreatePrintGenerator;
	final ErrorHandler errorHandler;

	final JTextField typeText = new JTextField(25);
	final JTextField sizeText = new JTextField(5);
	final JButton setBtn = new JButton("Create");

	public ArrayTypeInputPanel(final AutowiredGenerator generator, final AutowiredService service) {
		super(new JPanel(), generator, service);
		this.reflectionService = this.service.reflectionService;
		this.arrayCreatePrintGenerator = this.generator.arrayCreatePrintGenerator;
		this.errorHandler = this.generator.errorHandler;

		this.view.add(typeText);
		this.view.add(sizeText);
		this.view.add(setBtn);
		setBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			reflectionService.setArray(typeText.getText(), sizeText.getText());
			arrayCreatePrintGenerator.execute();
		} catch (Exception e1) {
			errorHandler.execute(e1);
		}
	}
}
