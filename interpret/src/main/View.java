package main;

import java.awt.Container;

public abstract class View {
	public Container view;
	public final AutowiredGenerator generator;
	public final AutowiredService service;

	public View(final Container view, final AutowiredGenerator generator, final AutowiredService service) {
		this.view = view;
		this.generator = generator;
		this.service = service;
	}
}
