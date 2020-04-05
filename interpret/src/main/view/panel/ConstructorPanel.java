package main.view.panel;

import static java.awt.GridBagConstraints.*;
import static main.utils.StringUtils.*;

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

import main.clazz.Argument;
import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.ConstructorClearPrintGenerator;
import main.generator.ConstructorCreatePrintGenerator;
import main.generator.ConstructorPrintGenerator;
import main.generator.ErrorHandler;
import main.generator.PrintGenerator;
import main.service.ConstructorService;
import main.service.ReflectionService;
import main.view.Observer;
import main.view.View;
import main.view.field.ArgText;

public class ConstructorPanel extends View implements ActionListener, ItemListener, Observer {
	private final ConstructorService constructorService;
	private final ReflectionService reflectionService;
	private final ConstructorPrintGenerator constructorPrintGenerator;
	private final ConstructorClearPrintGenerator constructorClearPrintGenerator;
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
		this.constructorClearPrintGenerator = this.generator.constructorClearPrintGenerator;
		this.constructorClearPrintGenerator.addObserver(this);

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
		generateBtn.setEnabled(false);
		generateBtn.addActionListener(this);
	}

	public JComboBox<String> getConstructorComboBox() {
		return constructorComboBox;
	}

	public void clearConstructor() {
		this.constructorComboBox.removeAllItems();
		this.generateBtn.setEnabled(false);
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
			this.argsPanel.add(new ArgText(this.errorHandler).text);
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
		// コンストラクタを消去する命令がくるかもしれない。
		if (printGenerator instanceof ConstructorClearPrintGenerator) {
			update((ConstructorClearPrintGenerator) printGenerator);
			return;
		}

		final Constructor<?>[] constructors = reflectionService.getConstructor();
		final JComboBox<String> constructorComboBox = this.getConstructorComboBox();
		// 元々あった選択肢を削除
		if (constructorComboBox.getItemCount() > 0) {
			constructorComboBox.removeAllItems();
		}
		for (Constructor<?> constructor : constructors) {
			constructorComboBox.addItem(getNameAndParameter(constructor));
		}
		if (constructorComboBox.getItemCount() > 0) {
			this.generateBtn.setEnabled(true);
		} else {
			this.generateBtn.setEnabled(false);
		}
	}

	public void update(ConstructorClearPrintGenerator printGenerator) {
		this.getConstructorComboBox().removeAllItems();
		this.generateBtn.setEnabled(false);
	}
}
