package dc1_4;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDialogView extends Dialog {
	private static final MenuDialogView menuDialogView = new MenuDialogView(ClockView.getInstance(),
			"Property Setting");
	private final MenuService menuService = MenuService.getInstance();
	private final ClockService clockService = ClockService.getInstance();

	private MenuDialogView(Frame frame, String title) {
		super(frame, title, true);
		final GridBagLayout layout = new GridBagLayout();
		final Label fontLabel = new Label("Font");
		final Label fontSizeLabel = new Label("Font Size");
		final Label fontColorLabel = new Label("Font Color");
		final Label backColorLabel = new Label("Background Color");
		final Button cancelBtn = new MenuCancelButton();
		final Button okBtn = new MenuOkButton();

		setLayout(layout);
		setSize(400, 200);
		this.setLocation(clockService.getFrameX(), clockService.getFrameY());
		layout.setConstraints(fontLabel, createConstraints(0, 0, 2, 1, HORIZONTAL, WEST));
		layout.setConstraints(menuService.getFontChoice(), createConstraints(2, 0, 2, 1, HORIZONTAL, EAST));
		layout.setConstraints(fontSizeLabel, createConstraints(0, 1, 2, 1, HORIZONTAL, WEST));
		layout.setConstraints(menuService.getFontSizeChoice(), createConstraints(2, 1, 2, 1, HORIZONTAL, EAST));
		layout.setConstraints(fontColorLabel, createConstraints(0, 2, 2, 1, HORIZONTAL, WEST));
		layout.setConstraints(menuService.getFontColorChoice(), createConstraints(2, 2, 2, 1, HORIZONTAL, EAST));
		layout.setConstraints(backColorLabel, createConstraints(0, 3, 2, 1, HORIZONTAL, WEST));
		layout.setConstraints(menuService.getBackgroundColorChoice(), createConstraints(2, 3, 2, 1, HORIZONTAL, EAST));
		layout.setConstraints(cancelBtn, createConstraints(3, 5, 1, 1, NONE, SOUTH));
		layout.setConstraints(okBtn, createConstraints(3, 5, 1, 1, NONE, SOUTHEAST));
		add(fontLabel);
		add(fontSizeLabel);
		add(fontColorLabel);
		add(backColorLabel);
		add(menuService.getFontChoice());
		add(menuService.getFontSizeChoice());
		add(menuService.getFontColorChoice());
		add(menuService.getBackgroundColorChoice());
		add(cancelBtn);
		add(okBtn);

		// clockView = ClockView.getInstance();
		addWindowListener(new MenuWindowAdapter());
	}

	private final GridBagConstraints createConstraints(final int x, final int y, final int width, final int height,
			final int fill, final int anchor) {
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.fill = fill;
		constraints.anchor = anchor;
		return constraints;
	}

	public static final MenuDialogView getInstance() {
		return menuDialogView;
	}
}
