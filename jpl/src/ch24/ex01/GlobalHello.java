package ch24.ex01;

import java.util.ResourceBundle;

public class GlobalHello {
	public static void main(String[] args) {
		ResourceBundle res = ResourceBundle.getBundle(GlobalRes.class.getName());
		showMessage(res, args);
		res = ResourceBundle.getBundle("ch24.ex01.GlobalRes_en");
		showMessage(res, args);
		res = ResourceBundle.getBundle("ch24.ex01.GlobalRes_en_ha");
		showMessage(res, args);
	}

	private static void showMessage(ResourceBundle res, String[] args) {
		String msg;
		if (args.length > 0) {
			msg = res.getString(GlobalRes.GOODBYE);
		} else {
			msg = res.getString(GlobalRes.HELLO);
		}
		System.out.println(msg);
	}
}
