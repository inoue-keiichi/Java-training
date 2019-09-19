package src.ch01.ex14;

public class WalkManVer3 {
	private Music[] musics;

	public void display(Music music) {
		System.out.println("歌手："+ music.getArtistName());
		System.out.println("曲名："+ music.getMusicName());
	}
}
