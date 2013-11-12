package main.view.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.view.panel.drumpanel.BPMControlsPanel;

@SuppressWarnings("serial")
public class DrumPanel extends JPanel {

    private static DrumPanel drumPanel = null;
    private BPMControlsPanel bpmPanel;

    public static DrumPanel getInstance() {
        if (drumPanel == null) {
            drumPanel = new DrumPanel();
        }
        return drumPanel;
    }

    private DrumPanel() {
        super();
        setBackground(Color.ORANGE);
        buildDrumPanel();
    }

    private void buildDrumPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        addBPMPanel(gridBagConstraints);
    }

    private void addBPMPanel(GridBagConstraints gridBagConstraints) {
        bpmPanel = new BPMControlsPanel(this);
        // gridBagConstraints.
        add(bpmPanel, gridBagConstraints);
    }

}
