package dc2_3.view;

import javax.swing.JPopupMenu;

import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;

public class PopupView extends View {
	public final JPopupMenu view;

	public PopupView(DIGenerator generator, DIService service) {
		super(generator, service);
		this.view = new JPopupMenu();
	}

}
