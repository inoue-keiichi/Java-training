package main.array.member;

import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.SOUTH;
import static java.awt.GridBagConstraints.SOUTHEAST;
import static java.awt.GridBagConstraints.WEST;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
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

import main.array.ArrayReflectionService;

public class ConstructorDialog extends JDialog implements ItemListener {
	private static final ConstructorDialog constructorDialog = new ConstructorDialog(InterpretView.getInstance(),
			"Constructor Setting");
	private final ArrayReflectionService reflectionService = ArrayReflectionService.getInstance();

	private final JTextField typeText = new JTextField(10);
	private final JComboBox<String> constructorComboBox = new JComboBox<>();
	private final JLabel argsLabel = new JLabel("Argument: ");
	private final JPanel argsPanel = new JPanel();
	private final JButton cancelBtn = new JButton("Cancel");
	private final JButton okBtn = new JButton("OK");
	private final GridBagConstraints gbc = new GridBagConstraints();

	private ConstructorDialog(JFrame frame, String title) {
		super(frame, title, true);
		final GridBagLayout layout = new GridBagLayout();

		setLayout(layout);
		setSize(400, 200);
		// this.setLocation(clockService.getFrameX(), clockService.getFrameY());

		this.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.add(new JLabel("Type: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.add(typeText, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = WEST;
		this.add(new JLabel("Constructor: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.add(constructorComboBox, gbc);
		constructorComboBox.addItemListener(this);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = WEST;
		this.add(new JLabel("Argument: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.add(this.argsPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = WEST;
		this.add(new ConstructorCancelButton(), gbc);
		// cancelBtn.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = EAST;
		this.add(new ConstructorOkButton(), gbc);
		// okBtn.addActionListener(this);

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

	public static final ConstructorDialog getInstance() {
		return constructorDialog;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		reflectionService.setArgTypes(constructorComboBox.getSelectedIndex());
		this.createArgumentPanel(reflectionService.getConstructorArgments());

	}
}
