package dc4.frame.news.bar;

import java.util.ArrayList;
import java.util.List;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.NewsApiClient.ArticlesResponseCallback;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest.Builder;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import javafx.application.Platform;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

public class NewsBarService {
	private static String API_KEY = "e2ceb8478a18426d8e849e73ff52d1de";
	private static final NewsBarService newsService = new NewsBarService();
	private NewsApiClient newsApiClient;
	private ArticlesResponseCallback responseCallback;
	private List<Article> articles;

	private double height;

	private int next;
	private int maxArticleNum;

	private NewsBarService() {
		this.newsApiClient = new NewsApiClient(API_KEY);
		this.responseCallback = createResponseCallback();
		this.articles = new ArrayList<>();
		this.next = 0;
	}

	public static NewsBarService getInstance() {
		return newsService;
	}

	public void requestArticles(String country, List<String> categories) {
		Builder builder = createHeaderBuilder(country, categories);
		newsApiClient.getTopHeadlines(builder.build(), responseCallback);
	}

	private Builder createHeaderBuilder(String country, List<String> categories) {
		Builder builder = new TopHeadlinesRequest.Builder();
		if (country != null) {
			builder = builder.country(country);
		}
		if (categories != null && categories.size() > 0) {
			for (String category : categories) {
				builder = builder.category(category);
			}
		}
		return builder;
	}

	private ArticlesResponseCallback createResponseCallback() {
		return new ArticlesResponseCallback() {
			@Override
			public void onSuccess(ArticleResponse response) {
				articles = response.getArticles();
				maxArticleNum = response.getArticles().size();
				// JavaFXのウィジェットはJavaFXの自分のスレッド以外から触ろうとすることが禁じられている
				Platform.runLater(() -> NewsBarObservable.execute("articles", null, null));
			}

			@Override
			public void onFailure(Throwable throwable) {
				System.out.println(throwable.getMessage());
			}
		};
	}

	public void updateArticle(VBox pane, int count) {
		pane.getChildren().clear();
		pane.getChildren().add(new Hyperlink(articles.get(count).getTitle()));
	}

	public Article nextArticle() {
		if (this.articles.size() == 0) {
			return null;
		}
		if (this.next >= this.maxArticleNum) {
			this.next = 0;
		}
		Article article = this.articles.get(next);
		this.next++;
		return article;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
