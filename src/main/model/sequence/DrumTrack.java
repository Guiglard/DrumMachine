package main.model.sequence;

import java.util.ArrayList;

import javax.sound.midi.Track;

import main.model.DrumMachineModel;

/**
 * Class representing a list of drum beats which should be played at certain ticks.<br>
 * The drum track has a channel, instrument, and a general volume for each beat.
 */
public class DrumTrack {

    private Track track = null;
    private int channel = 9;
    private int pitch = 40;
    private int volume = 100;
    private DrumMachineModel drumMachine;
    private ArrayList<DrumBeat> drumBeats = new ArrayList<>();

    public DrumTrack(DrumMachineModel drumMachine) {
        this.drumMachine = drumMachine;
        resetTrack();
    }

    /**
     * <li>Deletes the existing {@link Track} ; 
     * <li>Creates a new {@link Track} ; 
     * <li>Clears and recreates all {@link DrumBeat} ; 
     * <li>Generates every {@link DrumBeat}'s event ;
     */
    public void resetTrack() {
        drumMachine.getSequence().deleteTrack(track);
        track = drumMachine.getSequence().createTrack();
        clearDrumBeats();
        createDrumBeats();
        generateAllDrumBeatEvents();
    }

    /*
     * Generates every {@link DrumBeat}'s event.<br> 
     * This method must be called after any modification on attributes such as : 
     * <li>channel ; <li>pitch ; <li>volume ; <li>muted ;
     */
    public void generateAllDrumBeatEvents() {
        for (DrumBeat db : drumBeats) {
            db.generateEvent();
        }
    }
    
    private void createDrumBeats() {
        for (int i = 0; i <= drumMachine.getTicksPerBeat(); i++) {
            drumBeats.add(new DrumBeat(this, i));
        }
    }

    private void clearDrumBeats() {
        drumBeats.clear();
    }

    public Track getTrack() {
        return track;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
        generateAllDrumBeatEvents();
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
        updateDrumBeatsAfterChange();
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
        updateDrumBeatsAfterChange();
    }

    public int getTick(int drumBeatOnTick) {
        return drumBeats.get(drumBeatOnTick).getTick();
    }

    public void setTick(int drumBeatOnTick, int tick) {
        drumBeats.get(drumBeatOnTick).setTick(tick);
        updateDrumBeatsAfterChange();
    }

    public boolean isMuted(int drumBeatOnTick) {
        return drumBeats.get(drumBeatOnTick).isMuted();
    }

    public void setMuted(int drumBeatOnTick, boolean muted) {
        drumBeats.get(drumBeatOnTick).setMuted(muted);
        updateDrumBeatsAfterChange();
    }
    
    public int getTicksPerBeat() {
        return drumMachine.getTicksPerBeat();
    }
    
    /** Forces the regeneration of all the drum beat's MidiEvent after a change on the settings. */
    private void updateDrumBeatsAfterChange() {
        generateAllDrumBeatEvents();
    }
}
