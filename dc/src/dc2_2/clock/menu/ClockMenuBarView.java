package dc2_2.clock.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;
import dc2_2.view.MenuBarView;

public class ClockMenuBarView extends MenuBarView implements ActionListener {
	final JMenu editMenu;
	final JMenuItem editMenuPropertyItem;
	final JDialog jdialog;

	public ClockMenuBarView(final DIGenerator generator, final DIService service) {
		super(generator, service);
		this.editMenu = new JMenu("Edit");
		this.editMenuPropertyItem = new JMenuItem("Property");
		this.jdialog = new PropertyDialogView(generator, service).view;
		this.editMenu.add(this.editMenuPropertyItem);
		this.view.add(this.editMenu);
		this.editMenuPropertyItem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jdialog.setVisible(true);

	}
}
