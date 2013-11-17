package main.view;

import main.controller.DrumController;
import main.model.DrumMachineModel;
import main.model.observer.BPMObserver;

public class DrumMachineView implements BPMObserver {

    private DrumMachineModel drumMachineModel;
    private DrumController drumController;

    private static DrumMachineView instance = null;

    public static DrumMachineView getInstance() {
        if (instance != null) {
            return instance;
        }
        throw new IllegalArgumentException("DrumMachineView not instanciated yet");
    }

    public DrumMachineView(DrumController controller, DrumMachineModel model) {
        drumMachineModel = model;
        drumController = controller;
        instance = this;
        registerObservers();
    }

    private void registerObservers() {
        drumMachineModel.registerObserver((BPMObserver) this);
    }

    public void showView() {
        MainWindow w = MainWindow.getInstance();
        w.buildMainWindow();
    }

    public DrumMachineModel getBeatModel() {
        return drumMachineModel;
    }

    public DrumController getDrumController() {
        return drumController;
    }

    @Override
    public void updateBPM() {
    }

}