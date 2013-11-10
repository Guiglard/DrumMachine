package main.view.panel.drumpanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import main.constants.Constants;
import main.view.button.StartStopButton;
import main.view.panel.MainPanel;

@SuppressWarnings("serial")
public class DrumMiddlePanel extends JPanel {

    public DrumMiddlePanel(MainPanel mainPanel) {
        int height = (int) (mainPanel.getPreferredSize().height * Constants.DRUM_MIDDLE_PANEL_WEIGHT);
        setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, height));
        add(new StartStopButton());
        setVisible(true);
    }

}
