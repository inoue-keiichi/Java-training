package main;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JFrame;

class InterpretWindowAdapter extends WindowAdapter {
	private final AutowiredService service;

	public InterpretWindowAdapter(AutowiredService service) {
		this.service = service;
	}

	public void windowClosing(WindowEvent e) {
		Map<String, Object> instanceMap = service.reflectionService.getInstances();
		for (String key : instanceMap.keySet()) {
			if (instanceMap.get(key) instanceof Frame) {
				((Frame) instanceMap.get(key)).dispose();
			} else if (instanceMap.get(key) instanceof JFrame) {
				JFrame jframe = (JFrame) instanceMap.get(key);
				jframe.dispose();
				;
			}
		}
	}
}
