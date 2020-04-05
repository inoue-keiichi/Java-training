package main.view;

import java.awt.Container;

import main.di.AutowiredGenerator;
import main.di.AutowiredService;

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
