package ch24.ex01;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GlobalRes_en_ha extends ResourceBundle {
	final Map<String, String> resource = new HashMap<String, String>() {
		{
			put("hello", "aloha");
			put("goodbye", "aloha");
		}
	};

	@Override
	protected Object handleGetObject(String key) {
		return resource.get(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return null;
	}

}
