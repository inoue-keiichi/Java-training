package dc1_2;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PropertyDialog extends Dialog implements ActionListener {
	private final PropertyDialogService propertyDialogService = new PropertyDialogService();
	static final PropertyDialog propertyDialog = new PropertyDialog(ClockLayout.layout, "Setting");

	PropertyDialog(Frame frame, String title) {
		super(frame, title, true);
		setLayout(new GridLayout(5, 2));
		add(new Label("Font"));
		add(propertyDialogService.fontChoice);
		add(new Label("Font Size"));
		add(propertyDialogService.fontSizeChoice);
		add(new Label("Font Color"));
		add(propertyDialogService.fontColorChoice);
		add(new Label("Background Color"));
		add(propertyDialogService.backgroundColorChoice);
		Button okBtn = new Button("OK");
		okBtn.setBounds(50, 80, 100, 30);
		okBtn.addActionListener(this);
		add(okBtn);
		setResizable(false);
		setSize(400, 200);
		
		addWindowListener(new PropertyDialogWindowAdapter());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ClockFrame.font = propertyDialogService.fontConverter(propertyDialogService.fontChoice.getSelectedItem());
		ClockFrame.fontSize = propertyDialogService
				.intConverter(propertyDialogService.fontSizeChoice.getSelectedItem());
		ClockFrame.fontColor = propertyDialogService
				.colorConverter(propertyDialogService.fontColorChoice.getSelectedItem());
		ClockLayout.backgroundColor = propertyDialogService
				.colorConverter(propertyDialogService.backgroundColorChoice.getSelectedItem());
		ClockLayout.layout.setBackground(ClockLayout.layout.backgroundColor);
		ClockLayout.layout.setBackground(ClockLayout.layout.backgroundColor);
		dispose();
		ClockLayout.layout.setSize(ClockFrame.fontSize * 5, ClockFrame.fontSize * 5);
	}
}
