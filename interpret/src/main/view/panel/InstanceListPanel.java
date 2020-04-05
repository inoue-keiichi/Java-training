package main.view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.PrintGenerator;
import main.view.Observer;
import main.view.View;
import main.view.dialog.InstanceDialog;

public class InstanceListPanel extends View implements Observer, ActionListener {
	private final DefaultListModel<String> model = new DefaultListModel<>();
	private final JList<String> list = new JList<>(model);
	private final JButton displayBtn = new JButton();
	private InstanceDialog instanceDialog;

	public InstanceListPanel(final AutowiredGenerator generator, final AutowiredService service) {
		super(new JPanel(), generator, service);
		generator.constructorCreatePrintGenerator.addObserver(this);
		generator.arrayCreatePrintGenerator.addObserver(this);

		view.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(50, 100));
		list.setLayoutOrientation(JList.VERTICAL);
		list.addListSelectionListener(new InstanceListSelectionListener());
		list.setDragEnabled(true);
		displayBtn.setEnabled(false);
		displayBtn.addActionListener(this);
		view.add(scrollPane, BorderLayout.CENTER);
		view.add(displayBtn, BorderLayout.SOUTH);
	}

	public void addList(final String str) {
		model.addElement(str);
	}

	private class InstanceListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				// The user is still manipulating the selection.
				return;
			}
			String instanceName = list.getSelectedValue();
			if (instanceName != null) {
				displayBtn.setText(instanceName);
				displayBtn.setEnabled(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Objects.equals(displayBtn.getText(), "")) {
			return;
		}
		//buttonに表示されているインスタンスを表示する
		instanceDialog = new InstanceDialog(displayBtn.getText(), generator, service);
		instanceDialog.view.setVisible(true);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		model.addElement((String) printGenerator.getItem());
	}
}
