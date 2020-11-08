package ch04.ex07;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// javafx.scene.layout.Borderを利用することでコントロールの境界を設定できる
public class Main extends Application {
	@Override
	public void start(final Stage stage) throws Exception {
		final Label label = new Label("ラベル");
		final Label label1 = new Label("ラベル");
		final BorderStroke borderStroke1 = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				BorderWidths.DEFAULT);
		final Border border1 = new Border(borderStroke1);
		label1.setBorder(border1);
		final Label label2 = new Label("ラベル");
		final BorderStroke borderStroke2 = new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, CornerRadii.EMPTY,
				BorderWidths.DEFAULT);
		final Border border2 = new Border(borderStroke2);
		label2.setBorder(border2);
		final Label label3 = new Label("ラベル");
		final BorderStroke borderStroke3 = new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(20),
				BorderWidths.DEFAULT);
		final Border border3 = new Border(borderStroke3);
		label3.setBorder(border3);
		final Label label4 = new Label("ラベル");
		final BorderStroke borderStroke4 = new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				new BorderWidths(1, 2, 3, 4));
		final Border border4 = new Border(borderStroke4);
		label4.setBorder(border4);

		final Pane pane = new HBox(10);
		pane.getChildren().addAll(label, label1, label2, label3, label4);
		final Scene scene = new Scene(pane, 300, 180);
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest((final WindowEvent event) -> {
			System.exit(0);
		});
	}
}
