package dc2_4.view;

import dc2_4.di.DIGenerator;
import dc2_4.di.DIService;

public class View {
	public final DIGenerator generator;
	public final DIService service;

	public View(final DIGenerator generator, final DIService service) {
		this.generator = generator;
		this.service = service;
	}
}