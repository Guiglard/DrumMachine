package main.view.controls;

import main.constants.Constants;
import main.model.DrumMachineModel;

@SuppressWarnings("serial")
public class TicksPerBeatSlider extends TimeSignatureSlider {

    public TicksPerBeatSlider() {
        super(HORIZONTAL, Constants.MIN_TICKS_PER_BEAT, Constants.MAX_TICKS_PER_BEAT, DrumMachineModel.getInstance().getTicksPerBeat());
    }

    @Override
    protected void sliderChanged(int newValue) {

    }

}
