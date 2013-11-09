package main.view.panel.drumpanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.constants.Constants;
import main.view.DrumMachineView;
import main.view.panel.MainPanel;

@SuppressWarnings("serial")
public class DrumStartStopPanel extends JPanel implements ActionListener {

    private JButton bouton = new JButton("Start");
    private JButton bouton2 = new JButton("Stop");

    public DrumStartStopPanel(MainPanel mainPanel) {
        int height = (int) (mainPanel.getPreferredSize().height * Constants.DRUM_START_STOP_PANEL_WEIGHT);
        setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, height));
        bouton.addActionListener(this);
        bouton2.addActionListener(this);
        this.add(bouton);
        this.add(bouton2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bouton) {
            DrumMachineView.getInstance().getDrumController().start();
        } else if (e.getSource() == bouton2) {
            DrumMachineView.getInstance().getDrumController().stop();
        }

    }

}
