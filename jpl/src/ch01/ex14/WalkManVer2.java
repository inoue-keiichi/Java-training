package src.ch01.ex14;

public class WalkManVer2 extends WalkMan {
	private Music[] musics;
	
	/**
	 * 引数で渡した音楽データを音として２端子から出力します。
	 *
	 * @param data  音楽データ
	 */
	public void listenByTwoPerson(Object data) {
		System.out.println("2端子を利用します");
		listen(data);
	}
}
