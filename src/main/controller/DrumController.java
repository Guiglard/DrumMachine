package main.controller;

import main.model.BeatModel;
import main.view.DrumMachineView;

public class DrumController {

    BeatModel model;
    DrumMachineView view;

    public DrumController(BeatModel model) {
        this.model = model;
        view = new DrumMachineView(this, model);
        model.initialize();
        view.showView();
    }

    public void start() {
        model.on();
    }

    public void stop() {
        model.off();
    }

}
