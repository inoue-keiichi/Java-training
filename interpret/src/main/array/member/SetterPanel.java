package main.array.member;

import static java.awt.GridBagConstraints.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.ArgText;
import main.ArrayField;
import main.Autowired;
import main.StringUtils;
import main.value.ReflectionService;

public class SetterPanel extends JPanel implements ActionListener {
	private ReflectionService reflectionService = Autowired.reflectionService;
	private SetterPrintGenerator setterPrintGenerator = Autowired.setterPrintGenerator;

	private final JComboBox<String> constructorComboBox = new JComboBox<>();
	private final ArrayField instanceText = new ArrayField();
	private final IndexComboBox indexComboBox = new IndexComboBox();
	private final JTextField elementText = new ArgText().text;
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JTextField argText = new ArgText().text;
	private final JButton setBtn = new JButton("Set");
	private final GridBagConstraints gbc = new GridBagConstraints();

	public SetterPanel() {
		// レイアウト
		this.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.add(new JLabel("Array: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.instanceText.addObserver(this.indexComboBox);
		this.add(this.instanceText.text, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = WEST;
		this.add(new JLabel("Index: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.add(this.indexComboBox.comboBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = WEST;
		this.add(new JLabel("Element : "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(elementText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = EAST;
		this.add(this.setBtn, gbc);
		this.setBtn.addActionListener(this);
	}

	public JComboBox<String> getConstructorComboBox() {
		return constructorComboBox;
	}

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
			Autowired.errorHandler.execute(e1);
		}
	}
}
