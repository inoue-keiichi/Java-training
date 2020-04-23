package dc2_2.di;

import dc2_2.clock.ClockFrameService;
import dc2_2.clock.time.TimeService;

public class DIService {
	public final TimeService timeService;
	public final ClockFrameService clockFrameService;

	public DIService() {
		timeService = new TimeService();
		clockFrameService = new ClockFrameService();
	}
}
