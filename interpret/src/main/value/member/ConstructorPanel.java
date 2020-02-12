package main.value.member;

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
import main.value.ReflectionService;

import static java.awt.GridBagConstraints.*;

import java.awt.Component;

public class ConstructorPanel extends JPanel implements ActionListener, ItemListener {
	private static final ConstructorPanel constructorPanel = new ConstructorPanel();
	// private ConstructorPrintGenerator constructorPrintGenerator = new
	// ConstructorPrintGenerator();
	private ReflectionService reflectionService = ReflectionService.getInstance();
	// private MethodPrintGenerator methodPrintGenerator =
	// MethodPrintGenerator.getInstance();
	// private FieldPrintGenerator fieldPrintGenerator =
	// FieldPrintGenerator.getInstance();
	private ConstructorCreatePrintGenerator constructorCreatePrintGenerator = ConstructorCreatePrintGenerator
			.getInstance();

	private final JComboBox<String> constructorComboBox = new JComboBox<>();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JButton generateBtn = new JButton("Create");
	private final GridBagConstraints gbc = new GridBagConstraints();

	private ConstructorPanel() {
		// コンストラクタ生成の観測者を追加
		// constructorPrintGenerator.addObserver(this);
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

//	@Override
//	public void update(PrintGenerator printGenerator) {
//
//	}

	public JComboBox<String> getConstructorComboBox() {
		return constructorComboBox;
	}

	public void createArgumentPanel(final Argument[] args) {
		this.argsPanel.removeAll();
		if (args == null) {
			this.remove(this.argsLabel);
			this.repaint();
			return;
		}
		for (Argument arg : args) {
			this.argsPanel.add(new JTextField(5));
		}
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.anchor = WEST;
		this.add(this.argsLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.anchor = EAST;
		this.add(this.argsPanel, gbc);
		this.repaint();
		this.argsPanel.repaint();
	}

	public static ConstructorPanel getInstance() {
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		reflectionService.setArgTypes(constructorComboBox.getSelectedIndex());
		this.createArgumentPanel(reflectionService.getConstructorArgments());
		// constructorPanel.createArgumentPanel(reflectionService.getConstructorArgments());
	}
}
