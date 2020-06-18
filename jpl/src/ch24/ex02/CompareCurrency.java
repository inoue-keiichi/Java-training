package ch24.ex02;

import java.util.Currency;
import java.util.Locale;

public class CompareCurrency {

	public static void main(String[] args) {
		final Locale[] locales = { Locale.JAPAN, Locale.CANADA, Locale.ITALY, Locale.US, Locale.UK, Locale.KOREA };
		final Currency[] currencies = { Currency.getInstance(Locale.JAPAN), Currency.getInstance(Locale.CANADA),
				Currency.getInstance(Locale.ITALY), Currency.getInstance(Locale.US), Currency.getInstance(Locale.UK),
				Currency.getInstance(Locale.KOREA) };

		showGraph(locales, currencies);

	}

	private static void showGraph(Locale[] locales, Currency[] currencies) {
		boolean isShow = true;
		System.out.print(String.format("%-13s", "Currency Code"));
		System.out.print(" | ");
		System.out.print(String.format("%-15s", "Locale"));
		System.out.println(" | Symbol");
		for (Currency currency : currencies) {
			System.out.println("------------------------------------------");
			for (Locale locale : locales) {
				if (isShow) {
					System.out.print(String.format("%-13s", currency.getCurrencyCode()));
					isShow = false;
				} else {
					System.out.print(String.format("%-13s", ""));
				}

				System.out.print(" | ");
				System.out.print(String.format("%-15s", locale.getDisplayCountry(Locale.ENGLISH)));
				System.out.print(" | ");
				System.out.println(currency.getSymbol(locale));
			}
			isShow = true;
		}
	}
}
