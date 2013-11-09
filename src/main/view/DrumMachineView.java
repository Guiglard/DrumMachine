package main.view;

import main.controller.DrumController;
import main.model.BeatModel;
import main.model.observer.BPMObserver;
import main.model.observer.BeatObserver;
import main.view.panel.DrumPanel;
import main.view.panel.MainPanel;

public class DrumMachineView implements BeatObserver, BPMObserver {

    private BeatModel beatModel;
    private DrumController drumController;

    private static DrumMachineView instance = null;

    public static DrumMachineView getInstance() {
        if (instance != null) {
            return instance;
        }
        throw new IllegalArgumentException("DrumMachineView not instanciated yet");
    }

    public DrumMachineView(DrumController controller, BeatModel model) {
        beatModel = model;
        drumController = controller;
        instance = this;
        model.registerObserver((BeatObserver) this);
        model.registerObserver((BPMObserver) this);
    }

    public void showView() {
        MainWindow w = MainWindow.getInstance();
        w.buildMainWindow();
    }

    public BeatModel getBeatModel() {
        return beatModel;
    }

    public DrumController getDrumController() {
        return drumController;
    }

    @Override
    public void updateBPM() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBeat() {
        MainPanel currentPanel = MainWindow.getInstance().getCurrentPanel();
        if (currentPanel instanceof DrumPanel) {
            ((DrumPanel) currentPanel).getDrumBottomPanel().updateBeat();
        }
    }

}
