package main.view;

import javax.swing.JFrame;

import main.view.labels.Labels;

@SuppressWarnings("serial")
public class TimeSignatureFrame extends JFrame {
    
    private static TimeSignatureFrame instance = null;
    
    public static TimeSignatureFrame getInstance() {
        if (instance == null) {
            instance = new TimeSignatureFrame();
        }
        return instance;
    }
    
    public TimeSignatureFrame() {
        super(Labels.TIME_SIGNATURE_CONTROLS);
        setLocationRelativeTo(MainWindow.getInstance());
        add(new TimeSignaturePanel());
        pack();
    }
    
}
