package ch04.ex09;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Main extends Application {

	@Override
	public void start(final Stage stage) throws Exception {
		final Pane pane = new Pane();
		final Scene scene = new Scene(pane, 600, 600);
		final SolarSystem solarSystem = new SolarSystem();
		solarSystem.getSun().centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
		solarSystem.getSun().centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
		pane.getChildren().add(solarSystem.getSun());
		pane.getChildren().addAll(solarSystem.getMercury().getShape(), solarSystem.getMercury().getPath());
		pane.getChildren().addAll(solarSystem.getVenus().getShape(), solarSystem.getVenus().getPath());
		pane.getChildren().addAll(solarSystem.getEarth().getShape(), solarSystem.getEarth().getPath());
		pane.getChildren().addAll(solarSystem.getMars().getShape(), solarSystem.getMars().getPath());
		pane.getChildren().addAll(solarSystem.getJupiter().getShape(), solarSystem.getJupiter().getPath());

		moveOnPathTransition(solarSystem.getMercury());
		moveOnPathTransition(solarSystem.getVenus());
		moveOnPathTransition(solarSystem.getEarth());
		moveOnPathTransition(solarSystem.getMars());
		moveOnPathTransition(solarSystem.getJupiter());

		stage.setScene(scene);
		stage.show();

		stage.setResizable(false);
		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});
	}

	private void moveOnPathTransition(final Planet planet) {
		final PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(planet.getPeriodMsec()));
		pathTransition.setNode(planet.getShape());
		pathTransition.setPath(planet.getPath());
		pathTransition.setCycleCount(Animation.INDEFINITE);

		pathTransition.play();
	}

}
