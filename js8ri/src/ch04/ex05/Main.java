package ch04.ex05;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private static final double GAUGE_UNIT = 4;
	private static final double GAUGE_WIDTH = 100;
	private static final double GAUGE_HEIGHT = 10;
	private static final double MAX_GAUGE_VALUE = GAUGE_WIDTH;
	private static final double MIN_GAUGE_VALUE = 0;
	private static final double INIT_GAUGE_VALUE = 20;

	public static <T, R> ObservableValue<R> observe(final Function<T, R> f, final ObservableValue<T> t) {
		return new ObjectBinding<R>() {
			{
				bind(t);
			}

			@Override
			protected R computeValue() {
				return f.apply(t.getValue());
			}
		};
	}

	public static <T, U, R> ObservableValue<R> observe(final BiFunction<T, U, R> f, final ObservableValue<T> t,
			final ObservableValue<U> u) {
		return new ObjectBinding<R>() {
			{
				bind(t, u);
			}

			@Override
			protected R computeValue() {
				return f.apply(t.getValue(), u.getValue());
			}
		};
	}

	@Override
	public void start(final Stage stage) throws Exception {
		// 構造
		final Button largerBtn = new Button("Larger");
		final Button smallerBtn = new Button("Smaller");
		final ToggleButton ableBtn = new ToggleButton("Disable");

		final Rectangle gaugeBackground = new Rectangle(GAUGE_WIDTH, GAUGE_HEIGHT, Color.WHITE);
		final Rectangle gaugeOutLine = new Rectangle(GAUGE_WIDTH, GAUGE_HEIGHT, null);
		gaugeOutLine.setStroke(Color.BLACK);
		final Rectangle gauge = new Rectangle(INIT_GAUGE_VALUE, GAUGE_HEIGHT, Color.RED);
		final StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.CENTER_LEFT);
		stackPane.getChildren().addAll(gaugeBackground, gauge, gaugeOutLine);

		final Pane pane = new HBox(5);
		pane.getChildren().addAll(smallerBtn, stackPane, largerBtn, ableBtn);
		final Scene scene = new Scene(pane, 300, 30);
		stage.setScene(scene);

		// イベント
		largerBtn.setOnAction(event -> {
			gauge.setWidth(gauge.getWidth() + GAUGE_UNIT);
		});
		smallerBtn.setOnAction(event -> {
			gauge.setWidth(gauge.getWidth() - GAUGE_UNIT);
		});
		ableBtn.setOnAction(event -> {
			if (ableBtn.getText().equals("Disable")) {
				ableBtn.setText("Able");
			} else {
				ableBtn.setText("Disable");
			}
		});
		largerBtn.disableProperty().bind(
				observe((t, u) -> t.doubleValue() >= 100 || !u, gauge.widthProperty(), ableBtn.selectedProperty()));
		smallerBtn.disableProperty()
				.bind(observe((t, u) -> t.doubleValue() <= 0 || !u, gauge.widthProperty(), ableBtn.selectedProperty()));
		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});

		stage.show();
	}

	;
}
