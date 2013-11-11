package main.view.panel.drumpanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import main.constants.ViewConstants;
import main.view.panel.MainPanel;

@SuppressWarnings("serial")
public class DrumBottomPanel extends JPanel {

    private BPMPanel bpmPanel;
    
    public DrumBottomPanel(MainPanel mainPanel) {
        super();
        setBackground(Color.LIGHT_GRAY);
        int height = (int) (mainPanel.getPreferredSize().height * ViewConstants.DRUM_BOTTOM_PANEL_WEIGHT);
        setPreferredSize(new Dimension(mainPanel.getPreferredSize().width -20, height-20));
        //setSize(new Dimension(mainPanel.getPreferredSize().width, height));
        bpmPanel = new BPMPanel(this);
        add(bpmPanel);
    }

    public void updateBeat() {
        if (getBackground() == Color.LIGHT_GRAY) {
            setBackground(Color.GRAY);
        } else {
            setBackground(Color.LIGHT_GRAY);
        }
    }
    
    public BPMPanel getBpmPanel() {
        return bpmPanel;
    }
    
}
