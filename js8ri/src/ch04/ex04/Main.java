package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	@Override
	public void start(final Stage stage) throws Exception {
		final Pane pane = new Pane();
		final Scene scene = new Scene(pane, 300, 300);
		final Circle circle = new Circle(10);
		pane.getChildren().add(circle);
		circle.centerXProperty().bind(Bindings.divide(stage.widthProperty(), 2));
		circle.centerYProperty().bind(Bindings.divide(stage.heightProperty(), 2));
		circle.radiusProperty().bind(Bindings.divide(stage.widthProperty(), 2));

		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});
	}
}
