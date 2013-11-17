package main.view.controls;

import main.constants.Constants;
import main.model.DrumMachineModel;

@SuppressWarnings("serial")
public class BeatsPerBarSlider extends TimeSignatureSlider {

    public BeatsPerBarSlider() {
        super(HORIZONTAL, Constants.MIN_BEATS_PER_BAR, Constants.MAX_BEATS_PER_BAR, DrumMachineModel.getInstance().getBeatsPerBar());
    }

    @Override
    protected void sliderChanged(int newValue) {

    }

}
