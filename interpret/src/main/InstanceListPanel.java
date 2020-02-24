package main;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.value.ReflectionService;

public class InstanceListPanel extends JPanel {
	private static final InstanceListPanel instanceListPanel = new InstanceListPanel();

	private final ReflectionService reflectionService = ReflectionService.getInstance();

	private final DefaultListModel<String> model = new DefaultListModel<>();
	private final JList<String> list = new JList<>(model);

	private InstanceListPanel() {
		setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(list);
		list.setLayoutOrientation(JList.VERTICAL);
		list.addListSelectionListener(new InstanceListSelectionListener());
		list.setDragEnabled(true);
		// list.setTransferHandler(new TransferHandler("text"));
		add(scrollPane, BorderLayout.CENTER);
	}

	public void addList(final String str) {
		model.addElement(str);
	}

	public static InstanceListPanel getInstance() {
		return instanceListPanel;
	}

	private class InstanceListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				// The user is still manipulating the selection.
				return;
			}
			String instanceName = list.getSelectedValue();
			Object instance = reflectionService.getInstances().get(instanceName);
			if (instanceName != null) {
				InstanceDialog instanceDialog = new InstanceDialog(instance);
				instanceDialog.setVisible(true);
			}
		}
	}
}
