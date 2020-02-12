package main.array.member;

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

import main.ErrorHandler;
import main.Observer;
import main.PrintGenerator;
import main.array.ArrayReflectionService;

public class ElementFieldPanel extends JPanel implements ActionListener {
	private static final ElementFieldPanel fieldPanel = new ElementFieldPanel();

	private final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();
	private final ElementFieldPrintGenerator fieldPrintGenerator = ElementFieldPrintGenerator.getInstance();
	private final ElementFieldUpdatePrintGenerator fieldUpdatePrintGenerator = ElementFieldUpdatePrintGenerator.getInstance();

	private final JComboBox<String> fieldComboBox = new JComboBox<>();
	private final JTextField fieldText = new JTextField(10);
	private final JButton updateBtn = new JButton("Update");
	private final GridBagConstraints gbc = new GridBagConstraints();

	private ElementFieldPanel() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.add(new JLabel("Index: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.add(new JTextField(5), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = WEST;
		this.add(new JLabel("Field: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.add(fieldComboBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = WEST;
		this.add(new JLabel("Value: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(fieldText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
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

//	@Override
//	public void update(PrintGenerator printGenerator) {
//
//	}

	public static ElementFieldPanel getInstance() {
		return fieldPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			fieldUpdatePrintGenerator.execute();
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
			ErrorHandler.getInstance().execute(e1);
		}
	}
}
