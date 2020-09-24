package dc2_3.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;

public class MenuView extends View {
	public final JMenu view;
	public final ButtonGroup group;
	public final List<JRadioButtonMenuItem> itemList;

	public MenuView(DIGenerator generator, DIService service) {
		super(generator, service);
		this.view = new JMenu();
		this.group = new ButtonGroup();
		this.itemList = new ArrayList<>();
	}

}
