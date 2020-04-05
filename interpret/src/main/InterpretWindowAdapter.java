package main;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JDialog;

import main.di.AutowiredService;

class InterpretWindowAdapter extends WindowAdapter {
	private final AutowiredService service;

	public InterpretWindowAdapter(AutowiredService service) {
		this.service = service;
	}

	public void windowClosing(WindowEvent e) {
		// interpretViewが持つWindowクラスのオブジェクトを開放する
		Map<String, Object> instanceMap = service.reflectionService.getInstances();
		for (String key : instanceMap.keySet()) {
			if (instanceMap.get(key) instanceof Window) {
				((Window) instanceMap.get(key)).dispose();
			}
		}
		// インスタンスの中身を表すダイアログを開放する
		for (JDialog dialog : service.interpretViewService.dialogs) {
			dialog.dispose();
		}
	}
}
