package main.array.member;

import static java.awt.GridBagConstraints.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Argument;
import main.ErrorHandler;
import main.Observer;
import main.PrintGenerator;
import main.array.ArrayReflectionService;

public class ElementMethodPanel extends JPanel implements Observer, ItemListener, ActionListener {
	private static final ElementMethodPanel methodPanel = new ElementMethodPanel();

	private final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();
	private final ElementMethodExecutePrintGenerator methodExecutePrintGenerator = ElementMethodExecutePrintGenerator
			.getInstance();

	private final JComboBox<String> memberComboBox = new JComboBox<>();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JButton executeBtn = new JButton("Execute");
	private final GridBagConstraints gbc = new GridBagConstraints();
	private final GridBagLayout layout = new GridBagLayout();

	private ElementMethodPanel() {
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
		this.add(new JLabel("Method: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.add(memberComboBox, gbc);
		memberComboBox.addItemListener(this);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = EAST;
		this.add(executeBtn, gbc);
		executeBtn.addActionListener(this);
	}

	public void createArgumentPanel(final Argument[] args) {
		this.argsPanel.removeAll();
		this.revalidate();
		if (args == null) {
			this.remove(this.argsLabel);
			this.repaint();
			return;
		}
		for (Argument arg : args) {
			this.argsPanel.add(new JTextField(5));
		}
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.gbc.anchor = WEST;
		this.add(this.argsLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.gbc.anchor = EAST;
		this.add(this.argsPanel, gbc);
		this.argsPanel.revalidate();
	}

	public JComboBox<String> getMethodCoomboBox() {
		return memberComboBox;
	}

	@Override
	public void update(PrintGenerator printGenerator) {

	}

	public JComboBox<String> getMethodComboBox() {
		return memberComboBox;
	}

	public JPanel getArgsPanel() {
		return argsPanel;
	}

	public static ElementMethodPanel getInstance() {
		return methodPanel;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		reflectionService.setMethodArgTypes(memberComboBox.getSelectedIndex());
		createArgumentPanel(reflectionService.getMethodArgments());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			methodExecutePrintGenerator.execute();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			ErrorHandler.getInstance().execute(e1);
		}
	}
}
