package dc2_2.view;

import javax.swing.JMenuBar;

import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;

public class MenuBarView extends View {
	public final JMenuBar view;

	public MenuBarView(DIGenerator generator, DIService service) {
		super(generator, service);
		this.view = new JMenuBar();
	}

}
