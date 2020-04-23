package dc2_2.view;

import javax.swing.JPanel;

import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;

public class PanelView extends View {
	public final JPanel view;

	public PanelView(final JPanel view, DIGenerator generator, DIService service) {
		super(generator, service);
		this.view = view;
	}

}
