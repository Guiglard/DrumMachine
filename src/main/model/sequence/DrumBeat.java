package main.model.sequence;

import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;
import static main.Utils.EventMakerUtil.makeEvent;

import javax.sound.midi.MidiEvent;

/**
 * The drum beat is a sound happening at a precise tick on a given track.<br>
 * With each modification of the settings of the track or beat, the {@link MidiEvent} must be regenerated.<br>
 */
public class DrumBeat {

    private boolean isMuted = false;
    private int tick;
    private DrumTrack drumTrack = null;
    private MidiEvent midiEventOn;
    private MidiEvent midiEventOff;

    public DrumBeat(DrumTrack drumTrack, int tick) {
        this.tick = tick;
        this.drumTrack = drumTrack;
    }

    public void generateEvent() {
        removeOnAndOffEvents();
        addOnAndOffEvents();
    }

    private void setMidiEventOn(MidiEvent midiEventOn) {
        drumTrack.getTrack().remove(this.midiEventOn);
        this.midiEventOn = midiEventOn;
        drumTrack.getTrack().add(midiEventOn);
    }

    private void setMidiEventOff(MidiEvent midiEventOff) {
        drumTrack.getTrack().remove(this.midiEventOff);
        this.midiEventOff = midiEventOff;
        drumTrack.getTrack().add(midiEventOff);
    }

    private void removeOnAndOffEvents() {
        if (midiEventOn != null) {
            drumTrack.getTrack().remove(midiEventOn);
        }
        if (midiEventOff != null) {
            drumTrack.getTrack().remove(midiEventOff);
        }
    }

    private void addOnAndOffEvents() {
        int volume = drumTrack.getVolume();
        if (isMuted) {
            volume = 0;
        }
        setMidiEventOn(makeEvent(NOTE_ON, drumTrack.getChannel(), drumTrack.getPitch(), volume, tick));
        if (tick < drumTrack.getTicksPerBeat() - 1) {
            setMidiEventOff(makeEvent(NOTE_OFF, drumTrack.getChannel(), drumTrack.getPitch(), volume, tick + 1));
        }
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        this.isMuted = muted;
        updateDrumBeatAfterChange();
    }

    private void updateDrumBeatAfterChange() {
        generateEvent();
    }
    
}
