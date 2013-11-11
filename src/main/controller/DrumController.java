package main.controller;

import main.model.DrumMachineModel;
import main.view.DrumMachineView;

public class DrumController {
    
    private static DrumController instance = null;
    private DrumMachineModel model;
    private DrumMachineView view;

    public static DrumController getInstance() {
        return instance;
    }
    
    public DrumController(DrumMachineModel model) {
        instance = this;
        this.model = model;
        view = new DrumMachineView(this, model);
        model.initialize();
        view.showView();
    }

    public void startStop() {
        if (model.isRunning()) {
            model.off();
        } else {
            model.on();
        }
    }
    
    public void increaseBPM() {
        int bpm = model.getBpm();
        model.setBpm(bpm + 1);
    }
    
    public void decreaseBPM() {
        int bpm = model.getBpm();
        model.setBpm(bpm - 1);
    }
  
    public void setBPM(int bpm) {
        model.setBpm(bpm);
    }

}
