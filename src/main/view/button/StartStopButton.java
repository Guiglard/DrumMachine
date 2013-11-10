package main.view.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.view.DrumMachineView;

@SuppressWarnings("serial")
public class StartStopButton extends JButton implements ActionListener {

    public StartStopButton() {
        this.setText("on off");;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click");
        DrumMachineView.getInstance().getDrumController().onOff();
    }
    
}
