package main.array.type;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Autowired;
import main.value.ReflectionService;

public class ArrayTypeInputPanel extends JPanel implements ActionListener {
	final ReflectionService reflectionService = Autowired.reflectionService;
	final ArrayCreatePrintGenerator arrayCreatePrintGenerator = Autowired.arrayCreatePrintGenerator;

	final JTextField typeText = new JTextField(20);
	final JTextField sizeText = new JTextField(5);
	final JButton setBtn = new JButton("Create");

	public ArrayTypeInputPanel() {
		this.add(typeText);
		this.add(sizeText);
		this.add(setBtn);
		setBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		reflectionService.setArray(typeText.getText(), sizeText.getText());

		try {
			arrayCreatePrintGenerator.execute();
		} catch (ClassNotFoundException e1) {
			Autowired.errorHandler.execute(e1);
		}
	}
}
