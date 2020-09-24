package dc2_2.view;

import dc2_2.di.DIGenerator;
import dc2_2.di.DIService;

public class View {
	public final DIGenerator generator;
	public final DIService service;

	public View(final DIGenerator generator, final DIService service) {
		this.generator = generator;
		this.service = service;
	}
}