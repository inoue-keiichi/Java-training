package dc2_3.view;

import javax.swing.JWindow;

import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;

public class FrameView extends View {
	public JWindow view;

	public FrameView(JWindow view, DIGenerator generator, DIService service) {
		super(generator, service);
		this.view = view;
	}

}
