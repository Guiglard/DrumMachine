package main.view.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.model.DrumMachineModel;
import main.model.observer.BeatObserver;
import main.view.controls.BeatBar;
import main.view.panel.drumpanel.BPMControlsPanel;

@SuppressWarnings("serial")
public class DrumPanel extends JPanel implements BeatObserver {

    private static DrumPanel drumPanel = null;
    private BPMControlsPanel bpmPanel;
    private BeatBar beatBar;

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
        registerObservers();
    }

    private void buildDrumPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        addBPMPanel(gridBagConstraints);
        addBeatBar(gridBagConstraints);
    }
    
    private void registerObservers() {
        DrumMachineModel.getInstance().registerObserver(this);
    }

    private void addBPMPanel(GridBagConstraints gridBagConstraints) {
        bpmPanel = new BPMControlsPanel(this);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        add(bpmPanel, gridBagConstraints);
    }
    
    private void addBeatBar(GridBagConstraints gridBagConstraints) {
        beatBar = new BeatBar();
        beatBar.setValue(0);
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        add(beatBar, gridBagConstraints);
    }

    @Override
    public void updateBeat() {
        beatBar.setValue(100);
    }

    @Override
    public void updateStarted() {
        bpmPanel.enableControls(false);
    }

    @Override
    public void updateEnded() {
        bpmPanel.enableControls(true);
    }

}