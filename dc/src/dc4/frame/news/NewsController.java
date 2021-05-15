package dc4.frame.news;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class NewsController implements Initializable {
	NewsService newsService;

	@FXML
	WebView webView;
	@FXML
	MediaView mediaView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		newsService = NewsService.getInstance();

		WebEngine webEngine = webView.getEngine();
		String url = newsService.getUrl();
		webEngine.load(url);
		//		webEngine.loadContent(
		//				"<html<body><iframe width=\"400\" height=\"400\" src=\"https://www.youtube.com/embed/v=yz_tgk1_zhs\"></iframe></body></html>");

		//webEngine.load(url);
		//		webEngine.locationProperty().addListener((obs, oldLocation, newLocation) -> {
		//			System.out.println("locationProperty oldLocation=" + oldLocation);
		//			System.out.println("locationProperty newLocation=" + newLocation);
		//		});

		//		Media media = new Media("http://www.youtube.com/watch?v=k0BWlvnBmIE");
		//		MediaPlayer mediaPlayer = new MediaPlayer(media);
		//		mediaView.setMediaPlayer(mediaPlayer);
		//		mediaPlayer.play();
	}

}
