package main.view.panel.drumpanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.controller.DrumController;
import main.view.controls.BeatBar;

@SuppressWarnings("serial")
public class BPMPanel extends JPanel implements ActionListener {
    
    private JLabel bpmLabel;
    private JTextField bpmTextField;
    private JButton setBPMButton;
    private JButton increaseBPMButton;
    private JButton decreaseBPMButton;
    private BeatBar beatBar;

    public BPMPanel(JPanel parentPanel) {
        super(new FlowLayout());
        setSize(200, 200);
        beatBar = new BeatBar();
        beatBar.setValue(0);
        bpmTextField = new JTextField(2);
        bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
        setBPMButton = new JButton("Set");
        setBPMButton.setSize(new Dimension(10, 40));
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
        setBPMButton.addActionListener(this);
        increaseBPMButton.addActionListener(this);
        decreaseBPMButton.addActionListener(this);
        bpmLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(bpmLabel);
        add(bpmTextField);
        add(setBPMButton);
        add(decreaseBPMButton);
        add(increaseBPMButton);
        add(beatBar);
    }

    public BeatBar getBeatBar() {
        return beatBar;
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == setBPMButton) {
            int bpm = Integer.parseInt(bpmTextField.getText());
            DrumController.getInstance().setBPM(bpm);
        } else if (event.getSource() == increaseBPMButton) {
            DrumController.getInstance().increaseBPM();
        } else if (event.getSource() == decreaseBPMButton) {
            DrumController.getInstance().decreaseBPM();
        }
    }
}
