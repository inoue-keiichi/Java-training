package dc4.frame.news;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class NewsController implements Initializable {
	NewsService newsService;

	@FXML
	WebView webView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		newsService = NewsService.getInstance();

		WebEngine webEngine = webView.getEngine();
		String url = newsService.getUrl();
		webEngine.load(url);
	}
}
