package main.controller;

import main.model.DrumMachineModel;
import main.view.DrumMachineView;

public class DrumController {

    DrumMachineModel model;
    DrumMachineView view;

    public DrumController(DrumMachineModel model) {
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
