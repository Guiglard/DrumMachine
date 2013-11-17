package main.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.view.controls.BeatsPerBarSlider;
import main.view.controls.TicksPerBeatSlider;
import main.view.labels.Labels;

/**
 * The panel that contains the 2 sliders and their respective labels for controlling the beats per bar and the ticks per beat.
 */
@SuppressWarnings("serial")
public class TimeSignaturePanel extends JPanel {

    private JLabel beatsPerBarLabel = new JLabel(Labels.BEATS_PER_BAR_LABEL, JLabel.CENTER);
    private JLabel ticksPerBeatLabel = new JLabel(Labels.TICKS_PER_BEAT_LABEL, JLabel.CENTER);
    private TicksPerBeatSlider ticksPerBeatSlider = new TicksPerBeatSlider();
    private BeatsPerBarSlider beatsPerBarSlider = new BeatsPerBarSlider();

    public TimeSignaturePanel() {
        setBackground(Color.ORANGE);
        setLayout(new GridLayout(2, 2));
        addComponents();
    }

    private void addComponents() {
        add(beatsPerBarLabel);
        add(ticksPerBeatLabel);
        add(beatsPerBarSlider);
        add(ticksPerBeatSlider);
    }
}