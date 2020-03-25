package main.array.member;

import static java.awt.GridBagConstraints.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Argument;
import main.InterpretView;

public class ConstructorDialog extends JDialog implements ItemListener {
	private static final ConstructorDialog constructorDialog = new ConstructorDialog(InterpretView.getInstance(),
			"Constructor Setting");
	// private final ArrayReflectionService reflectionService =
	// ArrayReflectionService.getInstance();

	private final JTextField typeText = new JTextField(20);
	private final JButton setBtn = new JButton("Set");
	private final JComboBox<String> constructorComboBox = new JComboBox<>();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JButton okBtn = new JButton("OK");
	private final JButton cancelBtn = new JButton("Cancel");
	private final GridBagConstraints gbc = new GridBagConstraints();

	private ConstructorDialog(JFrame frame, String title) {
		super(frame, title, true);
		final GridBagLayout layout = new GridBagLayout();

		setLayout(layout);
		setSize(450, 200);
		// this.setLocation(clockService.getFrameX(), clockService.getFrameY());

		this.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.add(new JLabel("Type: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = CENTER;
		this.add(typeText, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.add(this.setBtn, gbc);
		this.setBtn.addActionListener(new TypeSetListener());
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.add(new JLabel("Constructor: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.anchor = EAST;
		this.add(constructorComboBox, gbc);
		constructorComboBox.addItemListener(this);

//		gbc.gridx = 0;
//		gbc.gridy = 2;
//		gbc.anchor = WEST;
//		this.add(new JLabel("Argument: "), gbc);
//		gbc.gridx = 1;
//		gbc.gridy = 2;
//		gbc.anchor = EAST;
//		this.add(this.argsPanel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = WEST;
		this.add(this.cancelBtn, gbc);
		this.cancelBtn.addActionListener(new ConstructorCancelListener());
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = EAST;
		this.add(this.okBtn, gbc);
		this.okBtn.addActionListener(new ConstructorOkListener());
		// addWindowListener(new MenuWindowAdapter());
	}

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
		this.gbc.gridy = 2;
		this.gbc.gridwidth = 1;
		this.gbc.anchor = WEST;
		this.add(this.argsLabel, gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.gbc.gridwidth = 2;
		this.gbc.anchor = EAST;
		this.add(this.argsPanel, gbc);
		this.repaint();
		this.argsPanel.repaint();
	}

	public static final ConstructorDialog getInstance() {
		return constructorDialog;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// reflectionService.setArgTypes(constructorComboBox.getSelectedIndex());
		// this.createArgumentPanel(reflectionService.getConstructorArgments());
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		try {
//			reflectionService.setClazz(typeText.getText());
//		} catch (ClassNotFoundException e1) {
//			errorHandler.execute(e1);
//		}
//		// コンストラクタの項目を表示する
//		elementConstructorPrintGenerator.execute();
////		// メンバータブを表示する
////		try {
////			memberPrintGenerator.execute();
////		} catch (Throwable e1) {
////			errorHandler.execute(e1);
////		}
//	}

	public JTextField getTypeText() {
		return this.typeText;
	}
}
