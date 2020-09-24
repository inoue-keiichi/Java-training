package dc3_2.menu;

import java.util.ArrayList;
import java.util.List;

import dc3_2.utils.ColorUtils;

public class MenuDialogService {
	private static final MenuDialogService menuDialogService = new MenuDialogService();

	public static MenuDialogService getInstance() {
		return menuDialogService;
	}

	private MenuDialogService() {

	}

	public static final List<String> FONT_FAMILY_NAMES = javafx.scene.text.Font.getFamilies();

	public static final List<String> FONT_SIZES = new ArrayList<String>() {
		{
			for (int i = 50; i <= 250; i++) {
				this.add(Integer.toString(i));
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
