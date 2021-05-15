package dc4.frame.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dc4.frame.clock.ClockType;
import dc4.utils.ColorUtils;

public class MenuDialogService {
	private static final MenuDialogService menuDialogService = new MenuDialogService();

	public static MenuDialogService getInstance() {
		return menuDialogService;
	}

	private MenuDialogService() {

	}

	public static final List<ClockType> CLOCK_TYPES = new ArrayList<ClockType>() {
		{
			add(ClockType.DEGITAL);
			add(ClockType.ANALOG);
			add(ClockType.TETRIS);
		}
	};

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

	public static final List<Integer> ALARM_HOURS = new ArrayList<Integer>() {
		{
			for (int i = 0; i < 24; i++) {
				this.add(i);
			}
		}
	};

	public static final List<Integer> ALARM_MINUTES = new ArrayList<Integer>() {
		{
			for (int i = 0; i < 60; i++) {
				this.add(i);
			}
		}
	};

	public static final Map<String, String> COUNTRY = new LinkedHashMap<>() {
		{
			put("US", "us");
			put("Japan", "ja");
			put("China", "ch");
			put("Korea", "kr");
		}
	};

	public static final Map<String, String> MUSIC_SAMPLES = new LinkedHashMap<>() {
		{
			this.put("Sample1", new File("src/dc4/resource/nc205000.wav").toURI().toString());
			this.put("Sample2", new File("src/dc4/resource/nc205001.wav").toURI().toString());
			this.put("Sample3", new File("src/dc4/resource/001-sibutomo.mp3").toURI().toString());
		}
	};
}
