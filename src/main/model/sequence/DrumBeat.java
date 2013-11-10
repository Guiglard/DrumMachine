package main.model.sequence;

import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

public class DrumBeat {

    private boolean active = false;
    private int tick;
    private DrumTrack drumTrack;
    private MidiEvent midiEventOn;
    private MidiEvent midiEventOff;

    public DrumBeat(DrumTrack drumTrack, int tick) {
        this.drumTrack = drumTrack;
        this.tick = tick;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
        removeOnAndOffEvents();
        addOnAndOffEvents();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        if (!active) {
            removeOnAndOffEvents();
        }
        addOnAndOffEvents();
        this.active = active;
    }

    public MidiEvent getMidiEventOn() {
        return midiEventOn;
    }

    public MidiEvent getMidiEventOff() {
        return midiEventOn;
    }

    public void setMidiEventOn(MidiEvent midiEventOn) {
        drumTrack.getTrack().remove(this.midiEventOn);
        this.midiEventOn = midiEventOn;
        drumTrack.getTrack().add(midiEventOn);
    }

    public void setMidiEventOff(MidiEvent midiEventOff) {
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
        setMidiEventOn(makeEvent(NOTE_ON, drumTrack.getChannel(), drumTrack.getPitch(), drumTrack.getVolume(), tick));
        if (tick < drumTrack.getDrumBeats().size() - 1) {
            setMidiEventOff(makeEvent(NOTE_OFF, drumTrack.getChannel(), drumTrack.getPitch(), drumTrack.getVolume(), tick + 1));
        }
    }

    /**
     * @param command
     *            NOTE_ON could be interpreted as the start of a note whereas
     *            NOTE_OFF would be the release.
     */
    public static MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick) {
        ShortMessage a1 = new ShortMessage();
        try {
            a1.setMessage(command, channel, data1, data2);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return new MidiEvent(a1, tick);
    }

}
