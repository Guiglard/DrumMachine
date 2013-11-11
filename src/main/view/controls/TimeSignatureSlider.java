package main.view.controls;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public abstract class TimeSignatureSlider extends JSlider implements ChangeListener {

    protected TimeSignatureSlider(int orientation, int min, int max, int init) {
        super(orientation, min, max, init);
        setMajorTickSpacing(2);
        setMinorTickSpacing(1);
        setPaintTicks(true);
        setPaintLabels(true);
        addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        if (!getValueIsAdjusting()) {
            sliderChanged((int) getValue());

        }
    }

    protected abstract void sliderChanged(int newValue);

}
