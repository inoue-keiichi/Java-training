package dc2_3.view;

import dc2_3.di.DIGenerator;
import dc2_3.di.DIService;

public class View {
	public final DIGenerator generator;
	public final DIService service;

	public View(final DIGenerator generator, final DIService service) {
		this.generator = generator;
		this.service = service;
	}
}