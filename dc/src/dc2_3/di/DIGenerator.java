package dc2_3.di;

import dc2_3.clock.ClockWindowPrintGenerator;
import dc2_3.clock.time.TimePanelPrintGenerator;

public class DIGenerator {
	public final ClockWindowPrintGenerator clockFramePrintGenerator;
	public final TimePanelPrintGenerator timePanelPrintGenerator;

	public DIGenerator(final DIService service) {
		this.clockFramePrintGenerator = new ClockWindowPrintGenerator(service);
		this.timePanelPrintGenerator = new TimePanelPrintGenerator(service);
	}
}
