package main.view.panel;

import javax.swing.JPanel;

import main.view.MainWindow;

@SuppressWarnings("serial")
public abstract class MainPanel extends JPanel {

	private MainWindow mainWindow = null;
	
	public MainPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.mainWindow.setContentPane(this);
	}
	
	public void displayMe() {
		mainWindow.displayPanel(this);
	}
	
}
