package main.view.panel.drumpanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import main.controller.DrumController;
import main.model.DrumMachineModel;
import main.model.observer.BPMObserver;

@SuppressWarnings("serial")
public class BPMControlsPanel extends JPanel implements ActionListener, PropertyChangeListener, BPMObserver {

    private static BPMControlsPanel instance = null;
    private JFormattedTextField bpmTextField;
    private JButton setBPMButton;
    private JButton increaseBPMButton;
    private JButton decreaseBPMButton;
    private NumberFormat bpmFormat = NumberFormat.getIntegerInstance();
    // private BeatBar beatBar;

    /** Returns a previously instantiated BPMControlsPanel or fails :) */
    public static BPMControlsPanel getInstance() {
        if (instance != null) {
            return instance;
        }
        throw new IllegalArgumentException("BPMControlPanel not instanciated yet !");
    }

    public BPMControlsPanel(JPanel parentPanel) {
        super(new GridBagLayout());
        setBackground(Color.GREEN);
        initElements();
        placeElements();
        addListeners();
        DrumMachineModel.getInstance().registerObserver(this);
    }

    private void initElements() {
        // beatBar = new BeatBar();
        // beatBar.setValue(0);
        bpmTextField = new JFormattedTextField(bpmFormat);
        bpmTextField.setValue(DrumMachineModel.getInstance().getBpm());
        bpmTextField.setDocument(new LengthRestrictedDocument(3));
        setBPMButton = new JButton("Set");
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
    }

    private void addListeners() {
        setBPMButton.addActionListener(this);
        increaseBPMButton.addActionListener(this);
        decreaseBPMButton.addActionListener(this);
        bpmTextField.addActionListener(this);
        bpmTextField.addPropertyChangeListener("value", this);
    }

    private void placeElements() {
        GridBagConstraints constraints = new GridBagConstraints();
        placeBPMTextField(constraints);
        placeDecreaseBPMButton(constraints);
        placeSetBPMButton(constraints);
        placeIncreaseBPMButton(constraints);
        // add(beatBar);
    }

    private void placeIncreaseBPMButton(GridBagConstraints constraints) {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(increaseBPMButton, constraints);
    }

    private void placeSetBPMButton(GridBagConstraints constraints) {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(setBPMButton, constraints);
    }

    private void placeDecreaseBPMButton(GridBagConstraints constraints) {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(decreaseBPMButton, constraints);
    }

    private void placeBPMTextField(GridBagConstraints constraints) {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 40;
        constraints.weightx = 0.0;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(bpmTextField, constraints);
        constraints.ipady = 0; // resets it
        constraints.gridwidth = 1;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == setBPMButton) {
            System.out.println("set clicked");
            bpmSetEvent();
        } else if (event.getSource() == increaseBPMButton) {
            System.out.println("increase clicked");
            bpmIncreasedEvent();
        } else if (event.getSource() == decreaseBPMButton) {
            System.out.println("decreased clicked");
            bpmDecreasedEvent();
        } else {
            System.out.println("else");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        System.out.println("value changed");
        // bpmSetEvent();
    }

    private void bpmSetEvent() {
        // DrumController.getInstance().setBPM(((Number) bpmTextField.getValue()).intValue());
    }

    private void bpmIncreasedEvent() {
        DrumController.getInstance().increaseBPM();
    }

    private void bpmDecreasedEvent() {
        DrumController.getInstance().decreaseBPM();
    }

    public class LengthRestrictedDocument extends PlainDocument {

        private int limit;

        public LengthRestrictedDocument(int limit) {
            super();
            this.limit = limit;
        }

        public LengthRestrictedDocument(int limit, boolean upper) {
            super();
            this.limit = limit;
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str != null && (getLength() + str.length()) <= limit) {
                if (str.equals("5")) {
                super.insertString(offs, str, a);
            }}
        }
    }

    @Override
    public void updateBPM() {
        bpmTextField.setValue(DrumMachineModel.getInstance().getBpm());
    }

}