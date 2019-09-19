package src.ch01.ex14;

public class WalkManVer2 extends WalkMan {
	private Music[] musics;
	
	public void listenByTwoPerson(Music music) {
		System.out.println("2端子を利用します");
		listen(music);
	}
}
