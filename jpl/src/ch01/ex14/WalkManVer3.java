package ch01.ex14;

public class WalkManVer3 {
	private Music[] musics;

	/**
	 * ディスプレイに歌手名と曲名を表示させます。
	 *
	 * @param music 音楽データの情報
	 */
	public void display(Music music) {
		System.out.println("歌手："+ music.getArtistName());
		System.out.println("曲名："+ music.getMusicName());
	}
}
