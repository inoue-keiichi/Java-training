package dc2_2.view;

import javax.swing.JDialog;

import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;

public class DialogView extends View {
	public final JDialog view;

	public DialogView(DIGenerator generator, DIService service) {
		super(generator, service);
		this.view = new JDialog();
	}

}
