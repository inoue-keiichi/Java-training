package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		final Pane pane = new VBox(10);
		final Label label = new Label();
		label.setFont(Font.font(100));
		final TextField tf = new TextField("Hello");
		label.textProperty().bind(tf.textProperty());
		pane.getChildren().addAll(label, tf);
		final Scene scene = new Scene(pane, 300, 180);
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});
	}
}
