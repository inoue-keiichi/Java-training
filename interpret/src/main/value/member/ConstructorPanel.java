package main.value.member;

import static java.awt.GridBagConstraints.*;
import static main.StringUtils.*;

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

import main.ArgText;
import main.Argument;
import main.AutowiredGenerator;
import main.AutowiredService;
import main.ErrorHandler;
import main.Observer;
import main.PrintGenerator;
import main.View;
import main.value.ReflectionService;

public class ConstructorPanel extends View implements ActionListener, ItemListener, Observer {
	private final ConstructorService constructorService;
	private final ReflectionService reflectionService;
	private final ConstructorPrintGenerator constructorPrintGenerator;
	private final ConstructorCreatePrintGenerator constructorCreatePrintGenerator;
	private final ErrorHandler errorHandler;

	private final JComboBox<String> constructorComboBox = new JComboBox<>();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JButton generateBtn = new JButton("Create");
	private final GridBagConstraints gbc = new GridBagConstraints();

	public ConstructorPanel(final AutowiredGenerator generator, final AutowiredService service) {
		super(new JPanel(), generator, service);
		this.constructorService = this.service.constructorService;
		this.reflectionService = this.service.reflectionService;
		this.constructorCreatePrintGenerator = this.generator.constructorCreatePrintGenerator;
		this.errorHandler = this.generator.errorHandler;
		this.constructorPrintGenerator = this.generator.constructorPrintGenerator;
		this.constructorPrintGenerator.addObserver(this);

		// レイアウト
		this.view.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Constructor: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.view.add(constructorComboBox, gbc);
		constructorComboBox.addItemListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.view.add(generateBtn, gbc);
		generateBtn.addActionListener(this);
	}

	public JComboBox<String> getConstructorComboBox() {
		return constructorComboBox;
	}

	public void createArgumentPanel(final Argument[] args) {
		this.argsPanel.removeAll();
		this.view.revalidate();
		if (args == null) {
			this.view.remove(this.argsLabel);
			this.view.repaint();
			return;
		}
		for (Argument arg : args) {
			this.argsPanel.add(new ArgText().text);
		}
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.anchor = WEST;
		this.view.add(this.argsLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.anchor = EAST;
		this.view.add(this.argsPanel, gbc);
		this.argsPanel.revalidate();
	}

	public JPanel getArgsPanel() {
		return this.argsPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			constructorService.setConstructorPanel(this);
			this.constructorCreatePrintGenerator.execute();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchFieldException | SecurityException e1) {
			this.errorHandler.execute(e1);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		reflectionService.setArgTypes(constructorComboBox.getSelectedIndex());
		this.createArgumentPanel(reflectionService.getConstructorArgments());
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		final Constructor<?>[] constructors = reflectionService.getConstructor();
		final JComboBox<String> constructorComboBox = this.getConstructorComboBox();
		// 元々あった選択肢を削除
		if (constructorComboBox.getItemCount() > 0) {
			constructorComboBox.removeAllItems();
		}
		for (Constructor<?> constructor : constructors) {
			constructorComboBox.addItem(getNameAndParameter(constructor));
		}
	}
}
