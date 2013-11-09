package main.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import main.view.factory.MainPanelFactory;
import main.view.menu.CommonMenu;
import main.view.menu.DrumMachineMenu;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

    private static MainWindow mainWindow = null;
    private JMenuBar menuBar = new JMenuBar();


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

    public void displayPanel(JPanel currentPanel) {
        setContentPane(currentPanel);
        setVisible(true);
    }

    public static void initMainWindow() {
        getInstance().initMenuBar();
        getInstance().displayPanel(MainPanelFactory.getDrumPanel());
        getInstance().setVisible(true);
    }
    
    private void initMenuBar() {
        menuBar.add(CommonMenu.getInstance());
        menuBar.add(DrumMachineMenu.getInstance());
        this.setJMenuBar(menuBar);
    }

}
