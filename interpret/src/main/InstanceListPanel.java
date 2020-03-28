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

import main.value.ReflectionService;

public class InstanceListPanel extends JPanel implements Observer, ActionListener {
	//private static final InstanceListPanel instanceListPanel = new InstanceListPanel();

	private final ReflectionService reflectionService = Autowired.reflectionService;

	private final DefaultListModel<String> model = new DefaultListModel<>();
	private final JList<String> list = new JList<>(model);
	// private final JTextField textField = new JTextField();
	private final JButton displayBtn = new JButton();
	private InstanceDialog instanceDialog;

	public InstanceListPanel() {
		Autowired.constructorCreatePrintGenerator.addObserver(this);
		Autowired.arrayCreatePrintGenerator.addObserver(this);

		setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(50, 100));
		list.setLayoutOrientation(JList.VERTICAL);
		list.addListSelectionListener(new InstanceListSelectionListener());
		list.setDragEnabled(true);
		displayBtn.addActionListener(this);
		// list.setTransferHandler(new TransferHandler("text"));
		add(scrollPane, BorderLayout.CENTER);
		add(displayBtn, BorderLayout.SOUTH);
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
			//Object instance = reflectionService.getInstances().get(instanceName);
			if (instanceName != null) {
				displayBtn.setText(instanceName);
				//instanceDialog = new InstanceDialog(instance);
				//				instanceDialog.setVisible(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//buttonに表示されているインスタンスを表示する
		//Object instance = reflectionService.getInstances().get(displayBtn.getText());
		instanceDialog = new InstanceDialog(displayBtn.getText());
		instanceDialog.setVisible(true);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		model.addElement((String) printGenerator.getItem());
	}
}
