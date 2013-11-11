package main.view.controls;

import main.constants.Constants;
import main.model.DrumMachineModel;

@SuppressWarnings("serial")
public class TicksPerBeatSlider extends TimeSignatureSlider {

    public TicksPerBeatSlider() {
        super(VERTICAL, Constants.ONE_TICK_PER_BEAT, Constants.SIX_TICKS_PER_BEAT, DrumMachineModel.getInstance().getTicksPerBeat());
    }

    @Override
    protected void sliderChanged(int newValue) {

    }

}
