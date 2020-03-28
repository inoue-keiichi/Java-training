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
import main.ErrorHandler;
import main.StringUtils;
import main.value.ReflectionService;

public class SetterPanel extends JPanel implements ActionListener {
	// private static final SetterPanel setterPanel = new SetterPanel();
	// private ConstructorPrintGenerator constructorPrintGenerator = new
	// ConstructorPrintGenerator();
	// private ArrayReflectionService reflectionService =
	// ArrayReflectionService.getInstance();
	private ReflectionService reflectionService = ReflectionService.getInstance();
	// private ElementMethodPrintGenerator methodPrintGenerator =
	// ElementMethodPrintGenerator.getInstance();
	// private ElementFieldPrintGenerator fieldPrintGenerator =
	// ElementFieldPrintGenerator.getInstance();
	//	private ConstructorDialogCreatePrintGenerator constructorDialogCreatePrintGenerator = ConstructorDialogCreatePrintGenerator
	//			.getInstance();
	private SetterPrintGenerator setterPrintGenerator = SetterPrintGenerator.getInstance();
	//private SetterPrintGenerator setterPrintGenerator = Autowired.setterPrintGenerator;

	private final JComboBox<String> constructorComboBox = new JComboBox<>();
	//	private final ArgText instanceText = new ArgText();
	private final ArrayField instanceText = new ArrayField();
	//private final JTextField indexText = new JTextField(8);
	private final IndexComboBox indexComboBox = new IndexComboBox();
	private final JTextField elementText = new ArgText().text;
	//private final JButton elementBtn = new ElementButton();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JTextField argText = new ArgText().text;
	private final JButton setBtn = new JButton("Set");
	private final GridBagConstraints gbc = new GridBagConstraints();

	public SetterPanel() {
		// コンストラクタ生成の観測者を追加
		// constructorPrintGenerator.addObserver(this);
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
		//this.add(this.indexText, gbc);
		this.add(this.indexComboBox.comboBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = WEST;
		this.add(new JLabel("Element : "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(elementText, gbc);
		// this.add(this.elementBtn, gbc);
		//this.elementBtn.addActionListener(new ConstructorListener());
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = EAST;
		this.add(this.setBtn, gbc);
		this.setBtn.addActionListener(this);
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

	//	public static SetterPanel getInstance() {
	//		return setterPanel;
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
			ErrorHandler.getInstance().execute(e1);
		}
	}

	//	@Override
	//	public void itemStateChanged(ItemEvent e) {
	//		reflectionService.setArgTypes(constructorComboBox.getSelectedIndex());
	//		constructorPanel.createArgumentPanel(reflectionService.getConstructorArgments());
	//	}
}