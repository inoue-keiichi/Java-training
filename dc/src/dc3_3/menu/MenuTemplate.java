package dc3_3.menu;

import dc3_3.time.TimeService;
import dc3_3.time.TimeService.MenuKey;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.text.Font;

public class MenuTemplate {
	private final TimeService timeService;

	private final EventHandler<ActionEvent> eventHandler;

	public MenuTemplate(final Menu menu) {
		timeService = TimeService.getInstance();

		eventHandler = event -> {
			menu.getItems().stream().filter(item -> !item.equals(event.getSource())).forEach(item -> {
				((RadioMenuItem) item).setSelected(false);
			});

			final String itemText = ((MenuItem) event.getSource()).getText() != null
					? ((MenuItem) event.getSource()).getText()
					: ((MenuItem) event.getSource()).getId();
			setMenuValue(MenuKey.get(menu.getText()), itemText);
			MenuDialogObservable.getInstance().execute();
		};
	}

	private void setMenuValue(final MenuKey menuKey, final String item) {
		switch (menuKey) {
		case FONT_FAMILY:
			timeService.setFont(Font.font(item, timeService.getFont().getSize()));
			break;

		case FONT_SIZE:
			timeService.setFont(Font.font(timeService.getFont().getFamily(), Integer.valueOf(item)));
			break;

		case FONT_COLOR:
			timeService.setFontColor(item);
			break;

		case BACKGROUND_COLOR:
			timeService.setBackgroundColor(item);
			break;

		default:
			break;
		}
	}

	public EventHandler<ActionEvent> getEventHandler() {
		return eventHandler;
	}
}
