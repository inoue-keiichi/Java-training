package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	@Override
	public void start(final Stage stage) throws Exception {
		final BorderPane pane = new BorderPane();
		final Button topBtn = new Button("Top");
		BorderPane.setAlignment(topBtn, Pos.CENTER);
		pane.setTop(topBtn);
		final Button bottomBtn = new Button("Bottom");
		BorderPane.setAlignment(bottomBtn, Pos.CENTER);
		pane.setBottom(bottomBtn);
		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));
		final Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});
	}
}
