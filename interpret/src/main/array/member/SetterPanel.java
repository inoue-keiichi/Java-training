package main.array.member;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Constructor;
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

import static java.awt.GridBagConstraints.*;

import java.awt.Component;

public class ElementPanel extends JPanel implements ActionListener {
	private static final ElementPanel constructorPanel = new ElementPanel();
	// private ConstructorPrintGenerator constructorPrintGenerator = new
	// ConstructorPrintGenerator();
	private ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();
	private ElementMethodPrintGenerator methodPrintGenerator = ElementMethodPrintGenerator.getInstance();
	private ElementFieldPrintGenerator fieldPrintGenerator = ElementFieldPrintGenerator.getInstance();
	private ElementConstructorCreatePrintGenerator constructorCreatePrintGenerator = ElementConstructorCreatePrintGenerator
			.getInstance();

	private final JComboBox<String> constructorComboBox = new JComboBox<>();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JButton generateBtn = new JButton("Create");
	private final GridBagConstraints gbc = new GridBagConstraints();

	private ElementPanel() {
		// コンストラクタ生成の観測者を追加
		// constructorPrintGenerator.addObserver(this);
		// レイアウト
		this.setLayout(new GridBagLayout());
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
		this.add(new JLabel("Element : "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.add(new ConstructorButton(), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(new JButton("Set"), gbc);
	}

//	@Override
//	public void update(PrintGenerator printGenerator) {
//
//	}

	public JComboBox<String> getConstructorComboBox() {
		return constructorComboBox;
	}

//	public void createArgumentPanel(final Argument[] args) {
//		this.argsPanel.removeAll();
//		if (args == null) {
//			this.remove(this.argsLabel);
//			this.repaint();
//			return;
//		}
//		for (Argument arg : args) {
//			this.argsPanel.add(new JTextField(5));
//		}
//		this.gbc.gridx = 2;
//		this.gbc.gridy = 1;
//		this.gbc.anchor = WEST;
//		this.add(this.argsLabel, gbc);
//		this.gbc.gridx = 3;
//		this.gbc.gridy = 1;
//		this.gbc.anchor = EAST;
//		this.add(this.argsPanel, gbc);
//		this.repaint();
//		this.argsPanel.repaint();
//	}

	public static ElementPanel getInstance() {
		return constructorPanel;
	}

	public JPanel getArgsPanel() {
		return this.argsPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			constructorCreatePrintGenerator.execute();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			ErrorHandler.getInstance().execute(e1);
		}
	}

//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		reflectionService.setArgTypes(constructorComboBox.getSelectedIndex());
//		constructorPanel.createArgumentPanel(reflectionService.getConstructorArgments());
//	}
}
