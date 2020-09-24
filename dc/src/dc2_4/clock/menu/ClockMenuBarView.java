package dc2_4.clock.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;
import dc2_4.view.MenuBarView;

public class ClockMenuBarView extends MenuBarView implements ActionListener {
	final JMenu editMenu;
	final JMenuItem editMenuPropertyItem;
	final JDialog jdialog;
	final JFrame frame;

	public ClockMenuBarView(final DIGenerator generator, final DIService service, final JFrame frame)
			throws IOException {
		super(generator, service);
		this.editMenu = new JMenu("Edit");
		this.editMenuPropertyItem = new JMenuItem("Property");
		this.jdialog = new PropertyDialogView(generator, service).view;
		this.frame = frame;
		this.editMenu.add(this.editMenuPropertyItem);
		this.view.add(this.editMenu);
		this.editMenuPropertyItem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jdialog.setLocation(this.frame.getSize().width + this.service.clockFrameService.getFrameX(),
				this.service.clockFrameService.getFrameY());
		jdialog.setVisible(true);

	}
}
