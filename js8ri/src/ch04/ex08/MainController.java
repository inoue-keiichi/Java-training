package ch04.ex08;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Rectangle;

public class MainController implements Initializable {
	private static final double GAUGE_UNIT = 10;
	private static final double MAX_GAUGE_VALUE = 250;
	private static final double MIN_GAUGE_VALUE = 0;
	private static final String TEXT = "Click \"Smaller\" or \"Larger\"";
	private static final String TEXT_BI = "You can change \"Able\" to \"Disable\"";

	@FXML
	private Label label;
	@FXML
	private Button largerBtn;
	@FXML
	private Button smallerBtn;
	@FXML
	private Rectangle gauge;
	@FXML
	private Label labelBi;
	@FXML
	private Button largerBtnBi;
	@FXML
	private Button smallerBtnBi;
	@FXML
	private ToggleButton ableBtn;
	@FXML
	private Rectangle gaugeBi;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		label.setText(TEXT);
		labelBi.setText(TEXT_BI);
		largerBtn.disableProperty()
				.bind(MainService.observe(t -> t.doubleValue() >= MAX_GAUGE_VALUE, gauge.widthProperty()));
		smallerBtn.disableProperty()
				.bind(MainService.observe(t -> t.doubleValue() <= MIN_GAUGE_VALUE, gauge.widthProperty()));
		largerBtnBi.disableProperty().bind(MainService.observe((t, u) -> t.doubleValue() >= MAX_GAUGE_VALUE || !u,
				gaugeBi.widthProperty(), ableBtn.selectedProperty()));
		smallerBtnBi.disableProperty().bind(MainService.observe((t, u) -> t.doubleValue() <= MIN_GAUGE_VALUE || !u,
				gaugeBi.widthProperty(), ableBtn.selectedProperty()));
	}

	@FXML
	private void largerOnClick(final ActionEvent actionEvent) {
		gauge.setWidth(gauge.getWidth() + GAUGE_UNIT);
	}

	@FXML
	private void smallerOnClick(final ActionEvent actionEvent) {
		gauge.setWidth(gauge.getWidth() - GAUGE_UNIT);
	}

	@FXML
	private void largerOnClickBi(final ActionEvent actionEvent) {
		gaugeBi.setWidth(gaugeBi.getWidth() + GAUGE_UNIT);
	}

	@FXML
	private void smallerOnClickBi(final ActionEvent actionEvent) {
		gaugeBi.setWidth(gaugeBi.getWidth() - GAUGE_UNIT);
	}

	@FXML
	private void ableOnClick(final ActionEvent actionEvent) {
		if (ableBtn.getText().equals("Disable")) {
			ableBtn.setText("Able");
		} else {
			ableBtn.setText("Disable");
		}
	}
}
