package dc2_2.clock;

import java.awt.Color;

import dc2_2.interfaces.Service;

public class ClockFrameService implements Service {
	private Color backgroundColor = Color.black;

	public Color getBackGroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(final Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
