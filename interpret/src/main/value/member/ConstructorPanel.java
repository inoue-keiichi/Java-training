package main.value.member;

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

import main.ArgText;
import main.Argument;
import main.Autowired;
import main.value.ReflectionService;

public class ConstructorPanel extends JPanel implements ActionListener, ItemListener {
	//private final ConstructorService constructorService = Autowired.constructorService;

	private ReflectionService reflectionService = Autowired.reflectionService;
	private final JComboBox<String> constructorComboBox = new JComboBox<>();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JButton generateBtn = new JButton("Create");
	private final GridBagConstraints gbc = new GridBagConstraints();

	public ConstructorPanel() {
		// レイアウト
		this.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.add(new JLabel("Constructor: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.add(constructorComboBox, gbc);
		constructorComboBox.addItemListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(generateBtn, gbc);
		generateBtn.addActionListener(this);
	}

	public JComboBox<String> getConstructorComboBox() {
		return constructorComboBox;
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
			this.argsPanel.add(new ArgText().text);
		}
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.anchor = WEST;
		this.add(this.argsLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.anchor = EAST;
		this.add(this.argsPanel, gbc);
		this.argsPanel.revalidate();
	}

	public JPanel getArgsPanel() {
		return this.argsPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			//constructorService.setConstructorPanel(this);
			Autowired.constructorCreatePrintGenerator.execute();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchFieldException | SecurityException e1) {
			Autowired.errorHandler.execute(e1);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		//constructorService.setConstructorComboBox(constructorComboBox);
		reflectionService.setArgTypes(constructorComboBox.getSelectedIndex());
		this.createArgumentPanel(reflectionService.getConstructorArgments());
	}
}
