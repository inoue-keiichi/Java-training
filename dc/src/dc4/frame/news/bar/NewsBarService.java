package dc4.frame.news.bar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	ExecutorService es = Executors.newSingleThreadExecutor();
	private NewsApiClient newsApiClient;
	private ArticlesResponseCallback responseCallback;
	private List<Article> articlesCache;

	private int next;
	private int maxArticleNum;

	private NewsBarService() {
		this.newsApiClient = new NewsApiClient(API_KEY);
		this.responseCallback = createResponseCallback();
		this.articlesCache = new ArrayList<>();
		this.next = 0;
	}

	public static NewsBarService getInstance() {
		return newsService;
	}

	public void requestArticles(String country, List<String> categories) {
		if (articlesCache.size() > 1) {
			Platform.runLater(() -> NewsBarObservable.execute("articles", null, articlesCache));
			return;
		}
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
				articlesCache = response.getArticles();
				maxArticleNum = response.getArticles().size();
				// JavaFXのウィジェットはJavaFXの自分のスレッド以外から触ろうとすることが禁じられている
				Platform.runLater(() -> NewsBarObservable.execute("articles", null, response.getArticles()));
			}

			@Override
			public void onFailure(Throwable throwable) {
				System.out.println(throwable.getMessage());
			}
		};
	}

	public void updateArticle(VBox pane, int count) {
		pane.getChildren().clear();
		pane.getChildren().add(new Hyperlink(articlesCache.get(count).getTitle()));
	}

	public Article nextArticle() {
		if (this.articlesCache.size() == 0) {
			return null;
		}
		if (this.next >= this.maxArticleNum) {
			this.next = 0;
		}
		Article article = this.articlesCache.get(next);
		this.next++;
		return article;
	}

}
