package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.di.AutowiredService;

class InterpretWindowAdapter extends WindowAdapter {
	private final AutowiredService service;

	public InterpretWindowAdapter(AutowiredService service) {
		this.service = service;
	}

	public void windowClosing(WindowEvent e) {
		service.reflectionService.disposeAllWindows();
	}
}
