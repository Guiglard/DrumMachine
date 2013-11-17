package main.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

import main.view.TimeSignatureFrame;
import main.view.labels.Labels;

@SuppressWarnings("serial")
public class EditionMenu extends JMenu {

    private static EditionMenu editionMenu = null;
    private JCheckBoxMenuItem drumMachineButton = new JCheckBoxMenuItem(Labels.EDIT_TIME_SIGNATURE);
    private ButtonGroup buttonGroup = new ButtonGroup();

    public static EditionMenu getInstance() {
        if (editionMenu == null) {
            editionMenu = new EditionMenu();
        }
        return editionMenu;
    }

    private EditionMenu() {
        super(Labels.WINDOW);
        initCommonMenu();
    }

    private void initCommonMenu() {
        buttonGroup.add(drumMachineButton);
        add(drumMachineButton);
        drumMachineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TimeSignatureFrame.getInstance().setVisible(!TimeSignatureFrame.getInstance().isVisible());
            }
        });
    }

}