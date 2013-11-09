package main.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import main.view.menu.CommonMenu;
import main.view.menu.DrumMachineMenu;
import main.view.panel.DrumPanel;
import main.view.panel.MainPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

    private static MainWindow mainWindow = null;
    private JMenuBar menuBar = new JMenuBar();
    private MainPanel currentPanel;

    public static MainWindow getInstance() {
        if (mainWindow == null) {
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }

    private MainWindow() {
        setTitle("DrumMachine");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changePanel(MainPanel currentPanel) {
        this.currentPanel = currentPanel;
        setContentPane(currentPanel);
        pack();
        setVisible(true);
    }

    public MainPanel getCurrentPanel() {
        return currentPanel;
    }

    public void buildMainWindow() {
        getInstance().buildMenuBar();
        getInstance().changePanel(DrumPanel.getInstance());
        getInstance().setVisible(true);
    }
    
    private void buildMenuBar() {
        menuBar.add(CommonMenu.getInstance());
        menuBar.add(DrumMachineMenu.getInstance());
        this.setJMenuBar(menuBar);
    }

}
