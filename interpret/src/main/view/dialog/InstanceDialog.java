package main.view.dialog;

import java.awt.Component;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import main.di.AutowiredGenerator;
import main.di.AutowiredService;
import main.generator.PrintGenerator;
import main.service.DialogService;
import main.service.InterpretViewService;
import main.service.ReflectionService;
import main.view.Observer;
import main.view.View;

public class InstanceDialog extends View implements Observer {
	private JTable fieldTable;
	private JTable methodTable;
	private final DialogService dialogService = new DialogService();
	private final ReflectionService reflectionService;
	private final InterpretViewService interpretViewService;

	public InstanceDialog(final String instanceName, final AutowiredGenerator generator,
			final AutowiredService service) {
		super(new JDialog(), generator, service);
		reflectionService = service.reflectionService;
		interpretViewService = service.interpretViewService;
		Object instance = reflectionService.getInstances().get(instanceName);
		dialogService.setInstance(instance);
		dialogService.setInstanceName(instanceName);
		view.setSize(500, 400);
		view.add(createMemberInfoPanel(instance));
		// interpretを閉じたら同時に閉じるようにする
		service.interpretViewService.dialogs.add((JDialog) view);

		this.view.setLocation(this.interpretViewService.x,
				this.interpretViewService.y + this.interpretViewService.dimension.getSize().height);
	}

	private JPanel createMemberInfoPanel(final Object instance) {
		JPanel panel = null;

		if (instance.getClass().isArray()) {
			panel = createArrayInfoPanel(instance);
		} else {
			panel = createInstanceInfoPanel(instance);
		}

		return panel;
	}

	private JPanel createInstanceInfoPanel(final Object instance) {
		String className = dialogService.getClassName(instance);
		JTable[] memberTables = null;

		try {
			memberTables = dialogService.createMemberTables(instance);
		} catch (IllegalArgumentException e2) {
			// TODO 自動生成された catch ブロック
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO 自動生成された catch ブロック
			e2.printStackTrace();
		}
		fieldTable = memberTables[0];
		fieldTable.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldTable.setDragEnabled(true);
		fieldTable.setTransferHandler(new Handler());
		methodTable = memberTables[1];
		methodTable.setAlignmentX(Component.LEFT_ALIGNMENT);

		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);

		panel.add(new JLabel(className));
		panel.add(new JLabel("field"));
		panel.add(fieldTable);
		JScrollPane fieldJScroll = new JScrollPane(fieldTable);
		panel.add(fieldJScroll);
		panel.add(new JLabel("method"));
		JScrollPane methodJScroll = new JScrollPane(methodTable);
		panel.add(methodJScroll);

		return panel;
	}

	private JPanel createArrayInfoPanel(final Object instance) {
		String className = dialogService.getClassName(instance);
		JTable elementTable = null;

		try {
			elementTable = dialogService.createElementTable(instance);
		} catch (IllegalArgumentException e2) {
			// TODO 自動生成された catch ブロック
			e2.printStackTrace();
		}
		elementTable.setAlignmentX(Component.LEFT_ALIGNMENT);
		elementTable.setDragEnabled(true);
		elementTable.setTransferHandler(new Handler());

		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);

		panel.add(new JLabel(className));
		panel.add(new JLabel("element"));
		JScrollPane elementJScroll = new JScrollPane(elementTable);
		panel.add(elementJScroll);

		return panel;
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		// TODO 自動生成されたメソッド・スタブ

	}

	class Handler extends TransferHandler {
		@Override
		protected Transferable createTransferable(JComponent c) {
			JTable table = (JTable) c;
			String name = dialogService.getInstanceName();

			Object obj;
			if (dialogService.getInstance().getClass().isArray()) {
				name = name.replaceAll("\\[\\]", "");
				obj = table.getValueAt(table.getSelectedRow(), 0);
				return new StringSelection(name + "[" + obj + "]");
			} else {
				obj = table.getValueAt(table.getSelectedRow(), 1);
				return new StringSelection(name + "." + obj);
			}
		}

		@Override
		public int getSourceActions(JComponent c) {
			return COPY;
		}
	}
}
