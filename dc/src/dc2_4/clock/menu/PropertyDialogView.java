package dc2_4.clock.menu;

import static dc2_4.clock.utils.ConverterUtils.*;
import static java.awt.GridBagConstraints.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dc2_4.abstracts.PrintGenerator;
import dc2_4.clock.menu.icon.ColorIcon;
import dc2_4.clock.menu.icon.FontIcon;
import dc2_4.clock.time.TimeService.MenuKey;
import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;
import dc2_4.interfaces.ItemIcon;
import dc2_4.interfaces.Observer;
import dc2_4.view.DialogView;

public class PropertyDialogView extends DialogView implements Observer {
	private final PropertyService propertyService;
	private final PropertyDialogGenerator propertyGenerator;
	private final GridBagConstraints gbc;

	private final JComboBox<ItemIcon> fontComboBox;
	private final JComboBox<Integer> fontSizeComboBox;
	private final JComboBox<ItemIcon> fontColorComboBox;
	private final JComboBox<ItemIcon> backgroundColorComboBox;

	//private final JLabel fontLabel;
	private final JPanel fontColorPanel;
	private final JPanel backgroundColorPanel;

	private String preFontColorName;
	private String preBackgroundColorName;
	private String preFontName;
	private int preFontSize;

	public PropertyDialogView(final DIGenerator generator, final DIService service) throws IOException {
		super(generator, service);
		this.propertyService = new PropertyService();
		this.propertyGenerator = new PropertyDialogGenerator(this.service);
		this.propertyGenerator.addObserver(this);

		//Init
		this.preFontColorName = service.timeService.load(MenuKey.FONT_COLOR);
		this.preBackgroundColorName = service.timeService.load(MenuKey.BACKGROUND_COLOR);
		this.preFontName = service.timeService.load(MenuKey.FONT_STYLE);
		this.preFontSize = Integer.parseInt(service.timeService.load(MenuKey.FONT_SIZE));

		//Component
		this.fontColorPanel = new JPanel();
		this.fontColorPanel.setBackground(Color.GREEN);
		this.backgroundColorPanel = new JPanel();
		this.backgroundColorPanel.setBackground(Color.BLACK);

		final CellRenderer cellRenderer = new CellRenderer();

		final DefaultComboBoxModel<ItemIcon> fontModel = new DefaultComboBoxModel<>();
		for (final Entry<String, Font> entry : PropertyUtility.FONTMAP.entrySet()) {
			fontModel.addElement(new FontIcon(entry.getValue()));
		}
		this.fontComboBox = new JComboBox<>(fontModel);
		this.fontComboBox.setRenderer(cellRenderer);
		this.fontComboBox
				.setSelectedItem(new FontIcon(PropertyUtility.FONTMAP.get(this.preFontName)));

		this.fontSizeComboBox = new JComboBox<>();
		for (final int fontSize : this.propertyService.getFontSizes()) {
			this.fontSizeComboBox.addItem(fontSize);
		}
		this.fontSizeComboBox.setSelectedItem(this.preFontSize);

		final DefaultComboBoxModel<ItemIcon> fontColorModel = new DefaultComboBoxModel<>();
		for (final String fontColor : this.propertyService.getColorNames()) {
			fontColorModel.addElement(new ColorIcon(fontColor, colorConverter(fontColor)));
		}
		this.fontColorComboBox = new JComboBox<>(fontColorModel);
		this.fontColorComboBox.setRenderer(cellRenderer);
		this.fontColorComboBox
				.setSelectedItem(new ColorIcon(this.preFontColorName, colorConverter(this.preFontColorName)));

		final DefaultComboBoxModel<ItemIcon> backgroundColorModel = new DefaultComboBoxModel<>();
		for (final String backgroundColor : this.propertyService.getColorNames()) {
			backgroundColorModel.addElement(new ColorIcon(backgroundColor, colorConverter(backgroundColor)));
		}
		this.backgroundColorComboBox = new JComboBox<>(backgroundColorModel);
		this.backgroundColorComboBox.setRenderer(cellRenderer);
		this.backgroundColorComboBox.setSelectedItem(
				new ColorIcon(this.preBackgroundColorName, colorConverter(this.preBackgroundColorName)));

		//Layout
		final GridBagLayout layout = new GridBagLayout();
		this.view.setLayout(layout);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Font"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Font Size"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Font Color"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Background Color"), gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = EAST;
		this.view.add(this.fontComboBox, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.anchor = EAST;
		this.view.add(this.fontSizeComboBox, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = EAST;
		this.view.add(this.fontColorComboBox, gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = EAST;
		this.view.add(this.backgroundColorComboBox, gbc);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.anchor = CENTER;
		final JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(50, 80, 100, 30);
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				fontComboBox.setSelectedItem(new FontIcon(PropertyUtility.FONTMAP.get(preFontName)));
				fontSizeComboBox.setSelectedItem(preFontSize);
				fontColorComboBox.setSelectedItem(new ColorIcon(preFontColorName, colorConverter(preFontColorName)));
				backgroundColorComboBox
						.setSelectedItem(new ColorIcon(preBackgroundColorName, colorConverter(preBackgroundColorName)));
				view.dispose();
			}

		});
		this.view.add(cancelBtn, gbc);
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.anchor = EAST;
		final JButton okBtn = new JButton("OK");
		okBtn.setBounds(50, 80, 100, 30);
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				service.timeService.setFont(((ItemIcon) fontComboBox.getSelectedItem()).getName());
				service.timeService.setFontSize((int) fontSizeComboBox.getSelectedItem());
				service.timeService.setFontColor(((ItemIcon) fontColorComboBox.getSelectedItem()).getName());
				service.timeService
						.setBackgroundColor(((ItemIcon) backgroundColorComboBox.getSelectedItem()).getName());
				service.clockFrameService
						.setBackgroundColor(
								colorConverter(((ItemIcon) backgroundColorComboBox.getSelectedItem()).getName()));
				generator.clockFramePrintGenerator.setChanged(true);
				generator.timePanelPrintGenerator.execute();
				view.dispose();
				//To preserve the parameters which are set now.
				preFontName = ((ItemIcon) fontComboBox.getSelectedItem()).getName();
				preFontSize = (int) fontSizeComboBox.getSelectedItem();
				preFontColorName = ((ItemIcon) fontColorComboBox.getSelectedItem()).getName();
				preBackgroundColorName = ((ItemIcon) backgroundColorComboBox.getSelectedItem()).getName();
			}

		});
		this.view.add(okBtn, gbc);
		this.view.setResizable(false);
		this.view.setSize(400, 200);
		this.view.setTitle("property setting");
		this.view.addWindowListener(this.propertyGenerator.window);
		//this.view.setLocation(this.service.clockFrameService.getFrameX(), this.service.clockFrameService.getFrameY());

		//Init
		//this.preFontColorName = (String) this.fontColorComboBox.getSelectedItem();
		//this.preBackgroundColorName = (String) this.backgroundColorComboBox.getSelectedItem();
		//this.preFontName = (String) this.fontComboBox.getSelectedItem();
		//this.preFontSize = (int) this.fontSizeComboBox.getSelectedItem();
	}

	@Override
	public void update(final PrintGenerator printGenerator) {
		this.fontComboBox.setSelectedItem(this.preFontName);
		this.fontSizeComboBox.setSelectedItem(this.preFontSize);
		this.fontColorComboBox.setSelectedItem(this.preFontColorName);
		this.backgroundColorComboBox.setSelectedItem(this.preBackgroundColorName);

	}
}
