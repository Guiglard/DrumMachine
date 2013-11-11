package main.view.panel.drumpanel;

import java.awt.Dimension;

import javax.swing.JPanel;

import main.constants.ViewConstants;
import main.view.panel.MainPanel;

@SuppressWarnings("serial")
public class DrumTopPanel extends JPanel {

    public DrumTopPanel(MainPanel mainPanel) {
        super();
        int height = (int) (mainPanel.getPreferredSize().height * ViewConstants.DRUM_TOP_PANEL_WEIGHT);
        setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, height));
    }
    
}
