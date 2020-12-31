package dc3_2.menu;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dc3_2.clock.ClockService;
import dc3_2.clock.ClockService.ClockType;
import dc3_2.frame.FrameService.ScreenMode;
import dc3_2.menu.cell.ColorCell;
import dc3_2.menu.cell.ColorModel;
import dc3_2.menu.cell.FontCell;
import dc3_2.menu.cell.FontModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MenuDialogController implements Initializable {
	private ClockService clockService;
	private Stage menuDialogStage;

	private ClockType oldClockType;

	@FXML
	private ComboBox<ClockType> clockTypeComboBox;
	@FXML
	private ComboBox<FontModel> fontComboBox;
	@FXML
	private ComboBox<String> fontSizeComboBox;
	@FXML
	private ComboBox<ColorModel> fontColorComboBox;
	@FXML
	private ComboBox<ColorModel> backgroundColorComboBox;

	@FXML
	private void onClickOk() {
		final String fontFamily = fontComboBox.getSelectionModel().getSelectedItem().toString();
		final int fontSize = Integer.valueOf(fontSizeComboBox.getSelectionModel().getSelectedItem());
		final String fontColor = fontColorComboBox.getSelectionModel().getSelectedItem().toString();
		final String backgroundColor = backgroundColorComboBox.getSelectionModel().getSelectedItem().toString();
		clockService.setClockType(clockTypeComboBox.getSelectionModel().getSelectedItem());
		clockService.setFont(Font.font(fontFamily, fontSize));
		clockService.setFontColor(fontColor);
		clockService.setBackgroundColor(backgroundColor);

		switch (clockTypeComboBox.getSelectionModel().getSelectedItem()) {
		case TETRIS:
			MenuDialogObservable.execute("", null, ScreenMode.TETRIS_CLOCK);
			break;
		case DEGITAL:
		case ANALOG:
			MenuDialogObservable.execute("", null, ScreenMode.CLOCK);
			break;
		default:
			throw new IllegalArgumentException();
		}

		menuDialogStage.close();
	}

	@FXML
	private void onClickCancel() {
		menuDialogStage.close();
	}

	public void initView(final Stage stage) {
		this.menuDialogStage = stage;
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		clockService = ClockService.getInstance();

		fontComboBox.setCellFactory(new Callback<ListView<FontModel>, ListCell<FontModel>>() {
			@Override
			public ListCell<FontModel> call(final ListView<FontModel> listView) {
				return new FontCell();
			}
		});

		fontColorComboBox.setCellFactory(new Callback<ListView<ColorModel>, ListCell<ColorModel>>() {
			@Override
			public ListCell<ColorModel> call(final ListView<ColorModel> listView) {
				return new ColorCell();
			}
		});

		backgroundColorComboBox.setCellFactory(new Callback<ListView<ColorModel>, ListCell<ColorModel>>() {
			@Override
			public ListCell<ColorModel> call(final ListView<ColorModel> listView) {
				return new ColorCell();
			}
		});

		clockTypeComboBox.addEventHandler(ActionEvent.ACTION, event -> {
			final ComboBox<?> ComboBox = (ComboBox<?>) event.getSource();
			final ClockType clockType = (ClockType) ComboBox.getSelectionModel().getSelectedItem();
			switch (clockType) {
			case DEGITAL:
				setFontDisable(false);
				break;
			case ANALOG:
				setFontDisable(true);
				break;
			case TETRIS:
				setFontDisable(true);
				backgroundColorComboBox.setDisable(true);
				break;
			}
		});

		clockTypeComboBox.setItems(FXCollections.observableArrayList(MenuDialogService.CLOCK_TYPES));
		fontComboBox.setItems(FXCollections.observableArrayList(createFontModels(MenuDialogService.FONT_FAMILY_NAMES)));
		fontSizeComboBox.setItems(FXCollections.observableArrayList(MenuDialogService.FONT_SIZES));
		fontColorComboBox.setItems(FXCollections.observableArrayList(createColorModels(MenuDialogService.COLORS)));
		backgroundColorComboBox
				.setItems(FXCollections.observableArrayList(createColorModels(MenuDialogService.COLORS)));

		// set default value.
		clockTypeComboBox.getSelectionModel().select(clockService.getClockType());
		switch (clockService.getClockType()) {
		case DEGITAL:
			setFontDisable(false);
			break;
		case ANALOG:
			setFontDisable(true);
			break;
		case TETRIS:
			setFontDisable(true);
			backgroundColorComboBox.setDisable(true);
			break;
		}
		fontComboBox.getSelectionModel().select(new FontModel(clockService.getFont().getFamily()));
		final int fontSize = Double.valueOf(clockService.getFont().getSize()).intValue();
		fontSizeComboBox.getSelectionModel().select(String.valueOf(fontSize));
		fontColorComboBox.getSelectionModel().select(new ColorModel(clockService.getFontColor()));
		backgroundColorComboBox.getSelectionModel().select(new ColorModel(clockService.getBackgroundColor()));
	}

	private ObservableList<FontModel> createFontModels(final List<String> fontFamilys) {
		final ObservableList<FontModel> fontModels = FXCollections.observableArrayList();
		for (final String fontFamily : fontFamilys) {
			final FontModel model = new FontModel(fontFamily);
			fontModels.add(model);
		}
		return fontModels;
	}

	private ObservableList<ColorModel> createColorModels(final List<String> colorNames) {
		final ObservableList<ColorModel> colorModels = FXCollections.observableArrayList();
		for (final String colorName : colorNames) {
			final ColorModel model = new ColorModel(colorName);
			colorModels.add(model);
		}
		return colorModels;
	}

	private void setFontDisable(final boolean value) {
		fontComboBox.setDisable(value);
		fontSizeComboBox.setDisable(value);
		fontColorComboBox.setDisable(value);
	}
}
