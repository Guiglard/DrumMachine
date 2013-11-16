package main.view.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.view.DrumMachineView;
import main.view.panel.drumpanel.BPMControlsPanel;

@SuppressWarnings("serial")
public class StartStopButton extends JButton implements ActionListener {

    private ImageIcon playIcon = new ImageIcon(BPMControlsPanel.class.getResource("../../../../resource/play.png"));
    
    public StartStopButton() {
        setIcon(playIcon);
        addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DrumMachineView.getInstance().getDrumController().startStop();
    }
    
}