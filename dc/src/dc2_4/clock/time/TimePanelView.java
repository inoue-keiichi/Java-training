package dc2_4.clock.time;

import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;
import dc2_4.view.PanelView;

public class TimePanelView extends PanelView {

	public TimePanelView(DIGenerator generator, DIService service) {
		super(new TimePanel(generator, service), generator, service);
	}

}
