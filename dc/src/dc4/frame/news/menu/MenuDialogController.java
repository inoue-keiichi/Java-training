package dc4.frame.news.menu;

import static dc4.frame.news.menu.MenuDialogService.NewsCategories.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dc4.frame.FrameService;
import dc4.interfaces.DialogController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class MenuDialogController implements Initializable, DialogController {
	private FrameService frameService;
	private Stage menuDialogStage;

	@FXML
	private CheckBox newsBarCheckBox;
	@FXML
	private ComboBox<String> newsCountryComboBox;
	@FXML
	private CheckBox newsAllCheckBox;
	@FXML
	private CheckBox newsBusinessCheckBox;
	@FXML
	private CheckBox newsEntertainmentCheckBox;
	@FXML
	private CheckBox newsGeneralCheckBox;
	@FXML
	private CheckBox newsHealthCheckBox;
	@FXML
	private CheckBox newsScienceCheckBox;
	@FXML
	private CheckBox newsSportsCheckBox;
	@FXML
	private CheckBox newsTechnologyCheckBox;

	@FXML
	private void onClickOk() {
		final String newsCountry = newsCountryComboBox.getSelectionModel().getSelectedItem();

		frameService.setNewsBarVisible(newsBarCheckBox.isSelected());
		frameService.setNewsCountry(MenuDialogService.COUNTRY.get(newsCountry));
		frameService.setNewsCategoryAll(newsAllCheckBox.isSelected());
		frameService.setNewsBusiness(newsBusinessCheckBox.isSelected());
		frameService.setNewsEntertainment(newsEntertainmentCheckBox.isSelected());
		frameService.setNewsGeneral(newsGeneralCheckBox.isSelected());
		frameService.setNewsHealth(newsHealthCheckBox.isSelected());
		frameService.setNewsScience(newsScienceCheckBox.isSelected());
		frameService.setNewsSports(newsSportsCheckBox.isSelected());
		frameService.setNewsTechnology(newsTechnologyCheckBox.isSelected());

		if (!newsAllCheckBox.isSelected()) {
			frameService.setNewsCategories(createCategories(newsBusinessCheckBox.isSelected(),
					newsEntertainmentCheckBox.isSelected(), newsGeneralCheckBox.isSelected(),
					newsHealthCheckBox.isSelected(),
					newsScienceCheckBox.isSelected(), newsSportsCheckBox.isSelected(),
					newsTechnologyCheckBox.isSelected()));
		}
		menuDialogStage.close();
		MenuDialogObservable.execute("newsBar", null, null);
	}

	private List<String> createCategories(boolean newsBusiness, boolean ewsEntertainment, boolean newsGeneral,
			boolean newsHealth, boolean newsScience, boolean newsSports, boolean newsTechnology) {
		final List<String> categories = new ArrayList<>();
		if (newsBusiness) {
			categories.add(BUSINESS.toString());
		}
		if (ewsEntertainment) {
			categories.add(ENTERTAINMENT.toString());
		}
		if (newsGeneral) {
			categories.add(GENERAL.toString());
		}
		if (newsHealth) {
			categories.add(HEALTH.toString());
		}
		if (newsScience) {
			categories.add(SCIENCE.toString());
		}
		if (newsSports) {
			categories.add(SPORTS.toString());
		}
		if (newsTechnology) {
			categories.add(TECHNOLOGY.toString());
		}
		return categories;
	}

	@FXML
	private void onClickCancel() {
		menuDialogStage.close();
	}

	@FXML
	public void changeNewsDisabled() {
		this.newsCountryComboBox.setDisable(!this.newsBarCheckBox.isSelected());
		this.newsAllCheckBox.setDisable(!this.newsBarCheckBox.isSelected());
		this.newsBusinessCheckBox.setDisable(!this.newsBarCheckBox.isSelected());
		this.newsEntertainmentCheckBox.setDisable(!this.newsBarCheckBox.isSelected());
		this.newsGeneralCheckBox.setDisable(!this.newsBarCheckBox.isSelected());
		this.newsHealthCheckBox.setDisable(!this.newsBarCheckBox.isSelected());
		this.newsScienceCheckBox.setDisable(!this.newsBarCheckBox.isSelected());
		this.newsSportsCheckBox.setDisable(!this.newsBarCheckBox.isSelected());
		this.newsTechnologyCheckBox.setDisable(!this.newsBarCheckBox.isSelected());
	}

	@FXML
	public void changeAllSelected() {
		this.newsBusinessCheckBox.setSelected(newsAllCheckBox.isSelected());
		this.newsEntertainmentCheckBox.setSelected(newsAllCheckBox.isSelected());
		this.newsGeneralCheckBox.setSelected(newsAllCheckBox.isSelected());
		this.newsHealthCheckBox.setSelected(newsAllCheckBox.isSelected());
		this.newsScienceCheckBox.setSelected(newsAllCheckBox.isSelected());
		this.newsSportsCheckBox.setSelected(newsAllCheckBox.isSelected());
		this.newsTechnologyCheckBox.setSelected(newsAllCheckBox.isSelected());
	}

	@FXML
	public void changeIndeterminateSelected() {
		if (newsBusinessCheckBox.isSelected() && newsEntertainmentCheckBox.isSelected()
				&& newsGeneralCheckBox.isSelected() && newsHealthCheckBox.isSelected()
				&& newsHealthCheckBox.isSelected() && newsSportsCheckBox.isSelected()
				&& newsTechnologyCheckBox.isSelected()) {
			this.newsAllCheckBox.setIndeterminate(false);
			this.newsAllCheckBox.setSelected(true);
			return;
		}
		this.newsAllCheckBox.setIndeterminate(true);
	}

	public void initView(final Stage stage) {
		this.menuDialogStage = stage;
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		frameService = FrameService.getInstance();

		newsCountryComboBox.setItems(FXCollections.observableArrayList(MenuDialogService.COUNTRY.keySet()));
		this.newsBarCheckBox.setSelected(frameService.getNewsBarVisible());
		this.newsCountryComboBox.getSelectionModel().select(frameService.getNewsCountry());
		this.newsAllCheckBox.setSelected(frameService.getNewsCategoryAll());
		this.newsBusinessCheckBox.setSelected(frameService.getNewsBusiness());
		this.newsEntertainmentCheckBox.setSelected(frameService.getNewsEntertainment());
		this.newsGeneralCheckBox.setSelected(frameService.getNewsGeneral());
		this.newsHealthCheckBox.setSelected(frameService.getNewsHealth());
		this.newsScienceCheckBox.setSelected(frameService.getNewsScience());
		this.newsSportsCheckBox.setSelected(frameService.getNewsSports());
		this.newsTechnologyCheckBox.setSelected(frameService.getNewsTechnology());
		changeNewsDisabled();
	}
}
