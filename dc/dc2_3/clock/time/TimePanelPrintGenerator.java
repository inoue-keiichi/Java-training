package dc2_3.clock.time;

import dc2_3.abstracts.PrintGenerator;
import dc2_3.di.DIService;

public class TimePanelPrintGenerator extends PrintGenerator {

	public TimePanelPrintGenerator(DIService service) {
		super(service);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void execute() {
		this.notifyObservers();
	}

}
