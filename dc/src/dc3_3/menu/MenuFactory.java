package dc3_3.menu;

import java.util.List;

import dc3_3.time.TimeService;
import dc3_3.time.TimeService.MenuKey;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuFactory {
	private static final TimeService timeService = TimeService.getInstance();

	public static Menu create(final MenuKey menuKey, final List<String> itemList) {
		final Menu menu = new Menu(menuKey.toString());
		final MenuTemplate menuTemplate = new MenuTemplate(menu);

		RadioMenuItem menuItem = null;
		for (final String item : itemList) {
			menuItem = createMenuItem(menuKey, item);
			menu.getItems().add(menuItem);
		}
		selectInitItem(menu, MenuKey.get(menu.getText()));
		menu.getItems().forEach(item -> {
			item.addEventHandler(ActionEvent.ACTION, menuTemplate.getEventHandler());
		});

		return menu;
	}

	//	public static <T> Menu create(final MenuKey menuKey, final Map<String, String> itemMap) {
	//		final Menu menu = new Menu(menuKey.toString());
	//		final MenuTemplate menuTemplate = new MenuTemplate(menu);
	//
	//		RadioMenuItem menuItem = null;
	//		for (final String item : itemMap) {
	//			menuItem = new RadioMenuItem(item);
	//			menu.getItems().add(menuItem);
	//		}
	//		selectInitItem(menu, MenuKey.get(menu.getText()));
	//		menu.getItems().forEach(item -> {
	//			item.addEventHandler(ActionEvent.ACTION, menuTemplate.getEventHandler());
	//		});
	//
	//		return menu;
	//	}

	private static RadioMenuItem createMenuItem(final MenuKey menuKey, final String item) {
		RadioMenuItem menuItem = null;
		switch (menuKey) {
		case FONT_FAMILY:
			final Text fontText = new Text(item);
			fontText.setFont(Font.font(item));
			menuItem = new RadioMenuItem(null, fontText);
			menuItem.setId(item);
			break;

		case FONT_SIZE:
			menuItem = new RadioMenuItem(item);
			break;

		case FONT_COLOR:
		case BACKGROUND_COLOR:
			menuItem = new RadioMenuItem(item, createRectangle(item));
			break;

		default:
			break;
		}

		return menuItem;
	}

	private static Canvas createRectangle(final String color) {
		final Canvas canvas = new Canvas(10, 10);

		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.valueOf(color));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

		return canvas;
	}

	private static void selectInitItem(final Menu menu, final MenuKey menuKey) {
		String itemText = null;

		switch (menuKey) {
		case FONT_FAMILY:
			itemText = timeService.getFont().getFamily();
			break;

		case FONT_SIZE:
			itemText = Integer.toString((int) timeService.getFont().getSize());
			break;

		case FONT_COLOR:
			itemText = timeService.getFontColor();
			break;

		case BACKGROUND_COLOR:
			itemText = timeService.getBackgroundColor();
			break;
		default:
			new IllegalArgumentException("value: " + itemText);
		}

		for (final MenuItem item : menu.getItems()) {
			if ((item.getText() != null && item.getText().equals(itemText))
					|| (item.getId() != null && item.getId().equals(itemText))) {
				((RadioMenuItem) item).setSelected(true);
				break;
			}
		}
	}
}
