package dc2_4.di;

import dc2_4.clock.ClockFramePrintGenerator;
import dc2_4.clock.time.TimePanelPrintGenerator;

public class DIGenerator {
	public final ClockFramePrintGenerator clockFramePrintGenerator;
	public final TimePanelPrintGenerator timePanelPrintGenerator;

	public DIGenerator(final DIService service) {
		this.clockFramePrintGenerator = new ClockFramePrintGenerator(service);
		this.timePanelPrintGenerator = new TimePanelPrintGenerator(service);
	}
}
