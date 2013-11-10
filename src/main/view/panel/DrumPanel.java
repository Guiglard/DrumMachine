package main.view.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import main.view.panel.drumpanel.DrumBottomPanel;
import main.view.panel.drumpanel.DrumMiddlePanel;
import main.view.panel.drumpanel.DrumTopPanel;

@SuppressWarnings("serial")
public class DrumPanel extends MainPanel {

    private static DrumPanel drumPanel = null;
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private DrumTopPanel drumTopPanel;
    private DrumMiddlePanel drumStartStopPanel;
    private DrumBottomPanel drumBottomPanel;
    
    public static DrumPanel getInstance() {
        if (drumPanel == null) {
            drumPanel = new DrumPanel();
        }
        return drumPanel;
    }

    private DrumPanel() {
        super();
        drumTopPanel = new DrumTopPanel(this);
        drumStartStopPanel = new DrumMiddlePanel(this);
        drumBottomPanel = new DrumBottomPanel(this);
        buildDrumPanel();
    }

    private void buildDrumPanel() {
        setLayout(new GridBagLayout());
        addDrumTopPanel();
        addDrumStartStopPanel();
        addBottomPanel();
    }

    private void addDrumTopPanel() {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(drumTopPanel, gridBagConstraints);
    }

    private void addDrumStartStopPanel() {
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(drumStartStopPanel, gridBagConstraints);
    }

    private void addBottomPanel() {
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        add(drumBottomPanel, gridBagConstraints);
    }

    public DrumTopPanel getDrumTopPanel() {
        return drumTopPanel;
    }

    public DrumMiddlePanel getDrumStartStopPanel() {
        return drumStartStopPanel;
    }

    public DrumBottomPanel getDrumBottomPanel() {
        return drumBottomPanel;
    }
}
