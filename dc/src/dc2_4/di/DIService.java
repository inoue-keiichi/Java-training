package dc2_4.di;

import dc2_4.clock.ClockFrameService;
import dc2_4.clock.time.TimeService;

public class DIService {
	public final TimeService timeService;
	public final ClockFrameService clockFrameService;

	public DIService() {
		timeService = new TimeService();
		clockFrameService = new ClockFrameService();
	}
}
