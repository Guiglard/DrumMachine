package main.view.panel;

import javax.swing.JPanel;

import main.view.MainWindow;

@SuppressWarnings("serial")
public abstract class MainPanel extends JPanel {

    public MainPanel() {
        setPreferredSize(MainWindow.getInstance().getSize());
    }

    public void displayMe() {
        MainWindow.getInstance().changePanel(this);
    }

}
