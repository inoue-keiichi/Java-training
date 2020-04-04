package main.array.member;

import static java.awt.GridBagConstraints.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.ArgText;
import main.ArrayField;
import main.AutowiredGenerator;
import main.AutowiredService;
import main.ErrorHandler;
import main.Observer;
import main.ObserverButton;
import main.PrintGenerator;
import main.StringUtils;
import main.View;
import main.value.ReflectionService;

public class SetterPanel extends View implements Observer, ActionListener {
	private ReflectionService reflectionService;
	private SetterPrintGenerator setterPrintGenerator;
	private SetterClearPrintGenerator setterClearPrintGenerator;
	private ErrorHandler errorHandler;

	//private final JComboBox<String> constructorComboBox;
	private final ArrayField instanceText;
	private final IndexComboBox indexComboBox;
	private final JTextField elementText;
	private final JLabel argsLabel;
	private final JPanel argsPanel;
	private final JTextField argText;
	private final ObserverButton setBtn;
	private final GridBagConstraints gbc;

	public SetterPanel(AutowiredGenerator generator, AutowiredService service) {
		super(new JPanel(), generator, service);
		this.reflectionService = this.service.reflectionService;
		this.setterPrintGenerator = this.generator.setterPrintGenerator;
		this.setterClearPrintGenerator = this.generator.setterClearPrintGenerator;
		this.errorHandler = this.generator.errorHandler;
		this.setterClearPrintGenerator.addObserver(this);

		//構成要素
		//this.constructorComboBox = new JComboBox<>();
		this.instanceText = new ArrayField(this.generator, this.service);
		this.indexComboBox = new IndexComboBox();
		this.elementText = new ArgText(this.errorHandler).text;
		this.argsLabel = new JLabel("Argument: ");
		this.argsPanel = new JPanel();
		this.argText = new ArgText(this.errorHandler).text;
		this.setBtn = new ObserverButton("Set");
		this.gbc = new GridBagConstraints();

		// レイアウト
		this.view.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Array: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		//this.instanceText.addObserver(this);
		this.instanceText.addObserver(this.indexComboBox);
		this.instanceText.addObserver(this.setBtn);
		this.instanceText.text.setEditable(false);
		this.view.add(this.instanceText.text, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Index: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.view.add(this.indexComboBox.comboBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Element : "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.view.add(elementText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = EAST;
		this.view.add(this.setBtn.btn, gbc);
		this.setBtn.btn.addActionListener(this);
	}

	//	public JComboBox<String> getConstructorComboBox() {
	//		return constructorComboBox;
	//	}

	public JPanel getArgsPanel() {
		return this.argsPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String instanceName = instanceText.text.getText();
		//  instanceのkeyがなかったらエラー
		if (!StringUtils.macthRegex(instanceName)) {
			throw new IllegalArgumentException();
		}
		// インスタンスがなかったらエラー
		String key = instanceName.substring(2, instanceName.length() - 1);
		Object instance = reflectionService.getInstances().get(key);
		if (Objects.isNull(instance)) {
			throw new NullPointerException(key);
		}

		String element = this.elementText.getText();
		try {
			setterPrintGenerator.execute(instance, (int) this.indexComboBox.comboBox.getSelectedItem(), element);
		} catch (Throwable e1) {
			errorHandler.execute(e1);
		}
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.indexComboBox.comboBox.removeAllItems();
		this.setBtn.btn.setEnabled(false);
		this.elementText.setText("");
	}
}
