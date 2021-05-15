package dc4.frame.news;

public class NewsService {
	private static final NewsService newsService = new NewsService();

	private String url;

	public static NewsService getInstance() {
		return newsService;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
