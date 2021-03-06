package main.view.controls;

import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class BeatBar extends JProgressBar implements Runnable { 
    JProgressBar progressBar;
    Thread thread;

    public BeatBar() {
        thread = new Thread(this);
        setOrientation(JProgressBar.VERTICAL);
        setValue(0);
        setMaximum(100);
        thread.start();
    }

    public void run() {
        for(;;) {
            int value = getValue();
            value = (int)(value * .75);
            setValue(value);
            repaint();
            try {
                Thread.sleep(40);
            } catch (Exception e) {};
        }
    }
    
}