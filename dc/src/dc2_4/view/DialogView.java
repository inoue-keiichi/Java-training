package dc2_4.view;

import javax.swing.JDialog;

import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;

public class DialogView extends View {
	public final JDialog view;

	public DialogView(DIGenerator generator, DIService service) {
		super(generator, service);
		this.view = new JDialog();
	}

}
