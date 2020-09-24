package dc2_2.clock.time;

import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;
import dc2_2.view.PanelView;

public class TimePanelView extends PanelView {

	public TimePanelView(DIGenerator generator, DIService service) {
		super(new TimePanel(generator, service), generator, service);
	}

}
