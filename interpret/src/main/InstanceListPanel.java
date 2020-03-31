package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//buttonに表示されているインスタンスを表示する
		instanceDialog = new InstanceDialog(displayBtn.getText(), generator, service);
		instanceDialog.view.setVisible(true);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		model.addElement((String) printGenerator.getItem());
	}
}
