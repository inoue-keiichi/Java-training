package dc4.frame.menu;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import dc4.frame.FrameService;
import dc4.frame.ScreenMode;
import dc4.frame.clock.ClockService;
import dc4.frame.clock.ClockType;
import dc4.frame.menu.cell.ColorCell;
import dc4.frame.menu.cell.ColorModel;
import dc4.frame.menu.cell.FontCell;
import dc4.frame.menu.cell.FontModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MenuDialogController implements Initializable {
	private FrameService frameService;
	private ClockService clockService;
	private Stage menuDialogStage;

	private ClockType oldClockType;
	private String musicFile;

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
	private ComboBox<String> musicSampleComboBox;
	@FXML
	private TextField musicFileText;
	@FXML
	private Button musicFileSelectButton;
	@FXML
	private Button musicFileDeleteButton;
	@FXML
	private ComboBox<Integer> alarmHourComboBox;
	@FXML
	private ComboBox<Integer> alarmMinuteComboBox;
	@FXML
	private CheckBox newsBarCheckBox;
	@FXML
	private ComboBox<String> newsCountryComboBox;

	@FXML
	private void onClickOk() {
		final String fontFamily = fontComboBox.getSelectionModel().getSelectedItem().toString();
		final int fontSize = Integer.valueOf(fontSizeComboBox.getSelectionModel().getSelectedItem());
		final String fontColor = fontColorComboBox.getSelectionModel().getSelectedItem().toString();
		final String backgroundColor = backgroundColorComboBox.getSelectionModel().getSelectedItem().toString();
		final String musicSample = musicSampleComboBox.getSelectionModel().getSelectedItem();
		final int alarmHour = alarmHourComboBox.getSelectionModel().getSelectedItem();
		final int alarmMinute = alarmMinuteComboBox.getSelectionModel().getSelectedItem();
		final String newsCountry = newsCountryComboBox.getSelectionModel().getSelectedItem();
		clockService.setClockType(clockTypeComboBox.getSelectionModel().getSelectedItem());
		clockService.setFont(Font.font(fontFamily, fontSize));
		clockService.setFontColor(fontColor);
		clockService.setBackgroundColor(backgroundColor);
		clockService.setMusic(musicSample);
		clockService.setCustomMusic(musicFile);
		clockService.setAlarmHour(alarmHour);
		clockService.setAlarmMinute(alarmMinute);
		frameService.setNewsBarVisible(newsBarCheckBox.isSelected());
		frameService.setNewsCountry(newsCountry);

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

	@FXML
	public void openMusicFileAction(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.setTitle("ファイル選択");
		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("音楽ファイル", "*.mp3", "*.wav"),
				new FileChooser.ExtensionFilter("すべてのファイル", "*.*"));
		// 初期フォルダをホームに
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		// ファイルダイアログ表示
		//File file = fc.showSaveDialog(null);
		File file = fc.showOpenDialog(null);
		if (file != null) {
			musicFileText.setText(file.getPath());
			musicFile = file.getPath();
		}
	}

	@FXML
	public void deleteMusicFileAction(ActionEvent e) {
		musicFileText.clear();
		clockService.setCustomMusic(null);
	}

	public void initView(final Stage stage) {
		this.menuDialogStage = stage;
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		frameService = FrameService.getInstance();
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
				backgroundColorComboBox.setDisable(false);
				break;
			case ANALOG:
				setFontDisable(true);
				backgroundColorComboBox.setDisable(false);
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
		musicSampleComboBox.setItems(FXCollections.observableArrayList(MenuDialogService.MUSIC_SAMPLES.keySet()));
		alarmHourComboBox.setItems(FXCollections.observableArrayList(MenuDialogService.ALARM_HOURS));
		alarmMinuteComboBox.setItems(FXCollections.observableArrayList(MenuDialogService.ALARM_MINUTES));
		Map<String, String> map = new HashMap<>();

		map.put("US", "us");
		map.put("Japan", "ja");
		map.put("China", "ch");
		map.put("Korea", "kr");


		newsCountryComboBox.setItems(FXCollections.observableArrayList(map.keySet()));

		// set default value.
		clockTypeComboBox.getSelectionModel().select(clockService.getClockType());
		switch (clockService.getClockType()) {
		case DEGITAL:
			setFontDisable(false);
			backgroundColorComboBox.setDisable(false);
			break;
		case ANALOG:
			setFontDisable(true);
			backgroundColorComboBox.setDisable(false);
			break;
		case TETRIS:
			setFontDisable(true);
			backgroundColorComboBox.setDisable(true);
			break;
		}
		fontComboBox.getSelectionModel().select(new FontModel(clockService.getFont().getFamily()));
		final int fontSize = Double.valueOf(clockService.getFont().getSize()).intValue();
		fontSizeComboBox.getSelectionModel().select(String.valueOf(fontSize));
		fontColorComboBox.getSelectionModel().select(new ColorModel(clockService.getFontColorName()));
		backgroundColorComboBox.getSelectionModel().select(new ColorModel(clockService.getBackgroundColorName()));
		musicSampleComboBox.getSelectionModel().select(clockService.getMusic());
		musicFileText.setText(clockService.getCustomMusic());
		alarmHourComboBox.getSelectionModel().select(clockService.getAlarmHour());
		alarmMinuteComboBox.getSelectionModel().select(clockService.getAlarmMinute());
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
