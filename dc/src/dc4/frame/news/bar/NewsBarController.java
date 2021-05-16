package dc4.frame.news.bar;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kwabenaberko.newsapilib.models.Article;

import dc4.frame.FrameService;
import dc4.frame.news.NewsObservable;
import dc4.frame.news.NewsService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewsBarController implements PropertyChangeListener, Initializable {
	NewsService newsService;
	NewsBarService newsBarService;
	NewsBarObservable newsObservable;
	FrameService frameService;
	int count = 0;

	ExecutorService es;

	@FXML
	VBox newsPane;

	Hyperlink hyperLink;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.newsService = NewsService.getInstance();
		this.newsBarService = NewsBarService.getInstance();
		this.frameService = FrameService.getInstance();
		this.es = Executors.newSingleThreadExecutor();

		// Add observer
		newsObservable = NewsBarObservable.getInstance();
		newsObservable.addPropertyChangeListener(this);

		this.newsBarService.requestArticles(frameService.getNewsCountry(), frameService.getNewsCategories());
	}

	public void initView(final Stage stage) {

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.es.execute(() -> {
			while (true) {
				final Article article = this.newsBarService.nextArticle();
				final Hyperlink hyperlink = new Hyperlink(article.getTitle());
				hyperlink.setOnAction(event -> {
					newsService.setUrl(article.getUrl());
					NewsObservable.execute("news", null, null);
					//System.out.println(article.getUrl());
				});
				Platform.runLater(() -> {
					this.newsPane.getChildren().clear();
					this.newsPane.getChildren().add(hyperlink);
				});
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		});

		//		this.newsService.updateArticle(this.newsPane, count);
		//		count++;

		//		try {
		//			Thread.sleep(5000);
		//		} catch (InterruptedException e) {
		//			// TODO 自動生成された catch ブロック
		//			e.printStackTrace();
		//		}
		//		this.newsService.updateArticle(this.newsPane, articles.get(1));

		//		this.hyperLink = new Hyperlink(articles.get(0).getTitle());
		//		this.newsPane.getChildren().clear();
		//		this.newsPane.getChildren().add(this.hyperLink);
	}

}
