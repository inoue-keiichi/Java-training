package ch05.ex02;

import java.time.LocalDate;

//実行結果
//2000年に1年加算->2001-02-28
//2000年に4年加算->2004-02-29
//2000年に1年を4回加算->2004-02-28
public class Main {
	public static void main(final String[] args) {
		System.out.println("2000年に1年加算->" + LocalDate.of(2000, 2, 29).plusYears(1));
		System.out.println("2000年に4年加算->" + LocalDate.of(2000, 2, 29).plusYears(4));
		System.out.println(
				"2000年に1年を4回加算->" + LocalDate.of(2000, 2, 29).plusYears(1).plusYears(1).plusYears(1).plusYears(1));
	}
}
