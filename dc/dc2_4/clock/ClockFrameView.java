package dc2_4.clock;

import java.awt.Dimension;

import javax.swing.JFrame;

import dc2_4.abstracts.PrintGenerator;
import dc2_4.clock.menu.ClockMenuBarView;
import dc2_4.clock.time.TimePanelView;
import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;
import dc2_4.interfaces.Observer;
import dc2_4.view.FrameView;

public class ClockFrameView extends FrameView implements Observer {
	private final TimePanelView timePanel;
	private final ClockMenuBarView menuBar;

	public ClockFrameView(final DIGenerator generator, final DIService service) {
		super(new JFrame(), generator, service);
		this.timePanel = new TimePanelView(this.generator, this.service);
		this.menuBar = new ClockMenuBarView(generator, service);
		this.view.setJMenuBar(this.menuBar.view);
		this.view.add(this.timePanel.view);
		this.view.setSize(500, 200);
		this.view.addWindowListener(new ClockWindowAdapter());
		this.view.setBackground(this.service.timeService.getBackgroundColor());
		this.view.setVisible(true);
		this.view.setMinimumSize(new Dimension(100, 100));

		// DI
		this.generator.clockFramePrintGenerator.addObserver(this);
	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.view.setSize(this.service.timeService.getFontSize() * 5, this.service.timeService.getFontSize() * 2);
		this.view.setBackground(this.service.timeService.getBackgroundColor());
	}
}
