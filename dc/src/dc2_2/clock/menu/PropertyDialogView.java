package dc2_2.clock.menu;

import static dc2_2.clock.utils.ConverterUtils.*;
import static java.awt.GridBagConstraints.*;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dc2_2.abstracts.PrintGenerator;
import dc2_2.clock.utils.ActionListenerAdapter;
import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;
import dc2_2.interfaces.Observer;
import dc2_2.view.DialogView;

public class PropertyDialogView extends DialogView implements ActionListener, Observer {
	private final PropertyService propertyService;
	private final PropertyDialogGenerator propertyGenerator;
	private final GridBagConstraints gbc;

	private final JComboBox<String> fontComboBox;
	private final JComboBox<Integer> fontSizeComboBox;
	private final JComboBox<String> fontColorComboBox;
	private final JComboBox<String> backgroundColorComboBox;

	//private final JLabel fontLabel;
	private final JPanel fontColorPanel;
	private final JPanel backgroundColorPanel;

	private String preFontColorName;
	private String preBackgroundColorName;
	private String preFontName;
	private int preFontSize;

	public PropertyDialogView(DIGenerator generator, DIService service) {
		super(generator, service);
		this.propertyService = new PropertyService();
		this.propertyGenerator = new PropertyDialogGenerator(this.service);
		this.propertyGenerator.addObserver(this);

		//Component
		this.fontColorPanel = new JPanel();
		this.fontColorPanel.setBackground(Color.GREEN);
		this.backgroundColorPanel = new JPanel();
		this.backgroundColorPanel.setBackground(Color.BLACK);
		this.fontComboBox = new JComboBox<>();
		for (final String font : this.propertyService.getFonts()) {
			this.fontComboBox.addItem(font);
		}
		this.fontSizeComboBox = new JComboBox<>();
		for (final int fontSize : this.propertyService.getFontSizes()) {
			this.fontSizeComboBox.addItem(fontSize);
		}
		this.fontColorComboBox = new JComboBox<>();
		for (final String fontColor : this.propertyService.getFontColorNames()) {
			this.fontColorComboBox.addItem(fontColor);
		}
		this.fontColorComboBox.addActionListener(new ActionListenerAdapter() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fontColorPanel
						.setBackground(colorConverter((String) fontColorComboBox.getSelectedItem()));
			}
		});
		this.backgroundColorComboBox = new JComboBox<>();
		for (final String backgroundColor : this.propertyService.getBackgroundColorNames()) {
			this.backgroundColorComboBox.addItem(backgroundColor);
		}
		this.backgroundColorComboBox.addActionListener(new ActionListenerAdapter() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundColorPanel
						.setBackground(
								colorConverter((String) backgroundColorComboBox.getSelectedItem()));
			}
		});

		//Layout
		GridBagLayout layout = new GridBagLayout();
		this.view.setLayout(layout);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Font"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Font Size"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Font Color"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = WEST;
		this.view.add(new JLabel("Background Color"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = EAST;
		this.view.add(this.fontComboBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = EAST;
		this.view.add(this.fontSizeComboBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.view.add(this.fontColorComboBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = EAST;
		this.view.add(this.backgroundColorComboBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = EAST;
		JButton okBtn = new JButton("OK");
		okBtn.setBounds(50, 80, 100, 30);
		okBtn.addActionListener(this);
		this.view.add(okBtn, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = EAST;
		this.view.add(fontColorPanel, gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = EAST;
		this.view.add(backgroundColorPanel, gbc);
		this.view.setResizable(false);
		this.view.setSize(600, 200);
		this.view.setTitle("property setting");
		this.view.addWindowListener(this.propertyGenerator.window);

		//Init
		this.preFontColorName = (String) this.fontColorComboBox.getSelectedItem();
		this.preBackgroundColorName = (String) this.backgroundColorComboBox.getSelectedItem();
		this.preFontName = (String) this.fontComboBox.getSelectedItem();
		this.preFontSize = (int) this.fontSizeComboBox.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.service.timeService.setFont((String) this.fontComboBox.getSelectedItem());
		this.service.timeService.setFontSize((int) this.fontSizeComboBox.getSelectedItem());
		this.service.timeService
				.setFontColor(colorConverter((String) this.fontColorComboBox.getSelectedItem()));
		this.service.timeService
				.setBackgroundColor(colorConverter((String) this.backgroundColorComboBox.getSelectedItem()));
		this.service.clockFrameService
				.setBackgroundColor(colorConverter((String) this.backgroundColorComboBox.getSelectedItem()));
		this.generator.clockFramePrintGenerator.execute();
		this.view.dispose();
		//To preserve the parameters which are set now.
		this.preFontName = (String) this.fontComboBox.getSelectedItem();
		this.preFontSize = (int) this.fontSizeComboBox.getSelectedItem();
		this.preFontColorName = (String) this.fontColorComboBox.getSelectedItem();
		this.preBackgroundColorName = (String) this.backgroundColorComboBox.getSelectedItem();

	}

	@Override
	public void update(PrintGenerator printGenerator) {
		this.fontComboBox.setSelectedItem(this.preFontName);
		this.fontSizeComboBox.setSelectedItem(this.preFontSize);
		this.fontColorComboBox.setSelectedItem(this.preFontColorName);
		this.backgroundColorComboBox.setSelectedItem(this.preBackgroundColorName);

	}
}
