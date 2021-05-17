package dc4.frame.news.menu;

import java.util.LinkedHashMap;
import java.util.Map;

public class MenuDialogService {
	private static final MenuDialogService menuDialogService = new MenuDialogService();

	private String country = "Japan";

	public static MenuDialogService getInstance() {
		return menuDialogService;
	}

	private MenuDialogService() {

	}

	public enum NewsCategories {
		BUSINESS("business"), ENTERTAINMENT("entertainment"), GENERAL("general"), HEALTH("health"), SCIENCE(
				"science"), SPORTS("sports"), TECHNOLOGY("technology");

		private String name;

		private NewsCategories(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}
	}

	public static final Map<String, String> COUNTRY = new LinkedHashMap<>() {
		{
			put("Japan", "jp");
			put("US", "us");
			put("China", "cn");
			put("Korea", "kr");
		}
	};

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
