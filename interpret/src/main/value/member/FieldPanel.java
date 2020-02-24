package main.value.member;

import static java.awt.GridBagConstraints.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Autowired;
import main.ErrorHandler;
import main.Observer;
import main.PrintGenerator;
import main.value.ReflectionService;

public class FieldPanel extends JPanel implements Observer, ActionListener {
	// private static final FieldPanel fieldPanel = new FieldPanel();

	// private final ReflectionService reflectionService =
	// ReflectionService.getInstance();
	// private final FieldPrintGenerator fieldPrintGenerator =
	// Autowired.fieldPrintGenerator;
	// private final FieldUpdatePrintGenerator fieldUpdatePrintGenerator =
	// Autowired.fieldUpdatePrintGenerator;

	private final JComboBox<String> fieldComboBox = new JComboBox<>();
	private final JTextField fieldText = new JTextField(10);
	private final JButton updateBtn = new JButton("Update");
	private final GridBagConstraints gbc = new GridBagConstraints();

	public FieldPanel() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.add(new JLabel("Field: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.add(fieldComboBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = WEST;
		this.add(new JLabel("Value: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.add(fieldText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(updateBtn, gbc);
		updateBtn.addActionListener(this);
	}

	public JComboBox<String> getFieldComboBox() {
		return this.fieldComboBox;
	}

	public String getInputText() {
		return this.fieldText.getText();
	}

	@Override
	public void update(PrintGenerator printGenerator) {

	}

//	public static FieldPanel getInstance() {
//		return fieldPanel;
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Autowired.fieldUpdatePrintGenerator.execute();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
			ErrorHandler.getInstance().execute(e1);
		}
	}
}
