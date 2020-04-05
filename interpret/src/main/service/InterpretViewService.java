package main.service;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

public class InterpretViewService implements Service {
	public int x;
	public int y;
	public Dimension dimension;
	public List<JDialog> dialogs = new ArrayList<JDialog>();
}
