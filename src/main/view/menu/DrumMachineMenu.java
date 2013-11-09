package main.view.menu;

import javax.swing.JMenu;

import main.view.labels.Labels;

@SuppressWarnings("serial")
public class DrumMachineMenu extends JMenu {
    
    private static DrumMachineMenu drumMachineMenu = null;
    
    public static DrumMachineMenu getInstance() {
        if (drumMachineMenu == null) {
            drumMachineMenu = new DrumMachineMenu();
        }
        return drumMachineMenu;
    }
    
    private DrumMachineMenu() {
        super(Labels.DRUM_MACHINE);
        initDrumMachineMenu();
    }
    
    private void initDrumMachineMenu() {
        
    }
    
}
