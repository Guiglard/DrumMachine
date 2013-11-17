package main.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import main.view.menu.EditionMenu;
import main.view.panel.DrumPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

    private static MainWindow mainWindow = null;
    private JMenuBar menuBar = new JMenuBar();
    private DrumPanel currentPanel;

    public static MainWindow getInstance() {
        if (mainWindow == null) {
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }

    private MainWindow() {
        setTitle("DrumMachine");
        // setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public DrumPanel getCurrentPanel() {
        return currentPanel;
    }

    public void buildMainWindow() {
        buildMenuBar();
        getContentPane().add(DrumPanel.getInstance());
        pack();
        setVisible(true);
    }

    private void buildMenuBar() {
        menuBar.add(EditionMenu.getInstance());
        this.setJMenuBar(menuBar);
    }

}
