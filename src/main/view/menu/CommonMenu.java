package main.view.menu;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import main.view.labels.Labels;

@SuppressWarnings("serial")
public class CommonMenu extends JMenu {

    private static CommonMenu commonMenu = null;

    public static CommonMenu getInstance() {
        if (commonMenu == null) {
            commonMenu = new CommonMenu();
        }
        return commonMenu;
    }

    private CommonMenu() {
        super(Labels.MODE);
        initCommonMenu();
    }

    private void initCommonMenu() {
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButtonMenuItem drumMachineButton = new JRadioButtonMenuItem(Labels.DRUM_MACHINE);
        buttonGroup.add(drumMachineButton);
        add(drumMachineButton);
        drumMachineButton.setSelected(true);
    }

}
