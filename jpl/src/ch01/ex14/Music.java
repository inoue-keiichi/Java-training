package ch01.ex14;

public class Music {
	private String musicName;
	private String artistName;
	private Object data;

	public Object getData() {
		return this.data;
	}

	public String getMusicName() {
		return this.musicName;
	}

	public String getArtistName() {
		return this.artistName;
	}

	public void setMusicId(Object data) {
		this.data = data;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
}
