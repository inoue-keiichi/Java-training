package src.ch01.ex14;

public class Music {
	private int musicId;
	private String musicName;
	private String artistName;

	public int getMusicId() {
		return this.musicId;
	}

	public String getMusicName() {
		return this.musicName;
	}

	public String getArtistName() {
		return this.artistName;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
}
