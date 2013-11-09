package main.view.factory;

import main.view.DrumPanel;
import main.view.MainWindow;

public class MainPanelFactory {
	protected static MainWindow mainWindow = MainWindow.getInstance();
	
	public static DrumPanel getDrumPanel() {
		return new DrumPanel(mainWindow);
	}
	
	
}
