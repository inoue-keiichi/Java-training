package dc3_3.menu;

import java.util.ArrayList;
import java.util.List;

import dc3_2.utils.ColorUtils;

public class MenuService {
	private static final MenuService menuService = new MenuService();

	public static MenuService getInstance() {
		return menuService;
	}

	private MenuService() {

	}

	public static final List<String> FONT_FAMILY_NAMES = javafx.scene.text.Font.getFamilies();

	public static final List<String> FONT_SIZES = new ArrayList<String>() {
		{
			for (int i = 5; i <= 25; i++) {
				this.add(Integer.toString(i * 10));
			}
		}
	};

	public static final List<String> COLORS = new ArrayList<String>() {
		{
			for (final String colorName : ColorUtils.colorNames()) {
				this.add(colorName);
			}
		}
	};
}
