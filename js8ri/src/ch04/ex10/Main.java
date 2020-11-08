package ch04.ex10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	@Override
	public void start(final Stage stage) throws Exception {
		final WebView webView = new WebView();
		final WebEngine webEngine = webView.getEngine();
		webEngine.load("https://www.google.com");

		final Button backBtn = new Button("Back");
		final Button nextBtn = new Button("Next");
		final Button searchBtn = new Button("Search");
		final TextField urlText = new TextField("https://www.google.com");
		urlText.setPrefWidth(400);
		webEngine.locationProperty().addListener(event -> {
			urlText.setText(webEngine.getLocation());
		});
		backBtn.setOnAction(event -> {
			try {
				webEngine.getHistory().go(-1);
			} catch (final IndexOutOfBoundsException e) {
				// 戻るページがないときは何もしない
			}
		});
		nextBtn.setOnAction(event -> {
			try {
				webEngine.getHistory().go(1);
			} catch (final IndexOutOfBoundsException e) {
				// 戻るページがないときは何もしない
			}
		});
		searchBtn.setOnAction(event -> {
			webEngine.load(urlText.getText());
		});

		final HBox browserBar = new HBox(5);
		browserBar.getChildren().addAll(backBtn, nextBtn, urlText, searchBtn);

		final BorderPane pane = new BorderPane();
		pane.setTop(browserBar);
		pane.setCenter(webView);
		final Scene scene = new Scene(pane, 600, 500);
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});
	}
}
