package main.view.panel.drumpanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import main.constants.Constants;
import main.view.panel.MainPanel;

@SuppressWarnings("serial")
public class DrumBottomPanel extends JPanel {

    public DrumBottomPanel(MainPanel mainPanel) {
        super();
        setBackground(Color.LIGHT_GRAY);
        int height = (int) (mainPanel.getPreferredSize().height * Constants.DRUM_BOTTOM_PANEL_WEIGHT);
        setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, height));
    }

    public void updateBeat() {
        if (getBackground() == Color.LIGHT_GRAY) {
            setBackground(Color.GRAY);
        } else {
            setBackground(Color.LIGHT_GRAY);
        }
    }
    
}
