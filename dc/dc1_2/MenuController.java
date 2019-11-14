package dc1_2;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController extends Dialog implements ActionListener {
	private static final MenuController menuController = new MenuController(ClockView.getInstance(),
			"Property Setting");
	private final MenuService menuService = MenuService.getInstance();
	private final ClockService clockService = ClockService.getInstance();

	private MenuController(Frame frame, String title) {
		super(frame, title, true);
		setLayout(new GridLayout(5, 2));
		add(new Label("Font"));
		add(menuService.getFontChoice());
		add(new Label("Font Size"));
		add(menuService.getFontSizeChoice());
		add(new Label("Font Color"));
		add(menuService.getFontColorChoice());
		add(new Label("Background Color"));
		add(menuService.getBackgroundColorChoice());
		Button okBtn = new Button("OK");
		okBtn.setBounds(50, 80, 100, 30);
		okBtn.addActionListener(this);
		add(new Label());
		add(okBtn);
		setResizable(false);
		setSize(400, 200);

		addWindowListener(new MenuWindowAdapter());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		clockService.setFont(menuService.fontConverter(menuService.getFontChoice().getSelectedItem()));
		clockService.setFontSize(menuService.intConverter(menuService.getFontSizeChoice().getSelectedItem()));
		clockService.setFontColor(menuService.colorConverter(menuService.getFontColorChoice().getSelectedItem()));
		clockService.setBackgroundColor(
				menuService.colorConverter(menuService.getBackgroundColorChoice().getSelectedItem()));
		dispose();
		ClockView.getInstance().setBackground(clockService.getBackgroundColor());
		ClockView.getInstance().setSize(clockService.getFontSize() * 5, clockService.getFontSize() * 5);
	}

	public static final MenuController getInstance() {
		return menuController;
	}
}
