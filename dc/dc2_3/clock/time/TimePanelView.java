package dc2_3.clock.time;

import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;
import dc2_3.view.PanelView;

public class TimePanelView extends PanelView {

	public TimePanelView(DIGenerator generator, DIService service) {
		super(new TimePanel(generator, service), generator, service);
	}

}
