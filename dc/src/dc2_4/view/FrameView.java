package dc2_4.view;

import javax.swing.JFrame;

import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;

public class FrameView extends View {
	public JFrame view;

	public FrameView(JFrame view, DIGenerator generator, DIService service) {
		super(generator, service);
		this.view = new JFrame();
	}

}
