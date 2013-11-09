package main;

import javax.swing.UIManager;

import main.controller.DrumController;
import main.model.BeatModel;
import main.model.DrumMachineModel;

public class DrumMachine {

    public static void main(String[] args) {
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        // Schedule a job for the event-dispatching thread:
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BeatModel model = new DrumMachineModel();
                new DrumController(model);
            }
        });
    }
}
