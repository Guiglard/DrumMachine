package main.model.sequence;

import static main.utils.EventMakerUtil.makeEvent;
import static main.utils.EventMakerUtil.makeMetaEvent;

import java.util.ArrayList;

import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import main.constants.Constants;
import main.model.DrumMachineModel;

/**
 * Class representing a list of drum beats which should be played at certain ticks.<br>
 * The drum track has a channel, instrument, and a general volume for each beat.
 */
public class DrumTrack {

    private Track track = null;
    private int channel = Constants.PERCUSSION_CHANNEL;
    private int pitch = 36;
    private int volume = Constants.VOLUME_MAX;
    private DrumMachineModel drumMachine;
    private ArrayList<DrumBeat> drumBeats = new ArrayList<>();

    public DrumTrack(DrumMachineModel drumMachine) {
        this.drumMachine = drumMachine;
        initTrack();
    }

    public DrumTrack(DrumMachineModel drumMachine, int volume, int pitch) {
        this.volume = volume;
        this.pitch = pitch;
        this.drumMachine = drumMachine;
        initTrack();
    }

    public DrumTrack(DrumMachineModel drumMachine, int volume, int pitch, boolean[] data) {
        this.volume = volume;
        this.pitch = pitch;
        this.drumMachine = drumMachine;
        initTrack();
        muteDrumBeats(data);
    }

    /**
     * <li>Deletes the existing {@link Track} ; 
     * <li>Creates a new {@link Track} ; 
     * <li>Clears and recreates all {@link DrumBeat} ; 
     * <li>Generates every {@link DrumBeat}'s event ;
     */
    public void initTrack() {
        drumMachine.getSequence().deleteTrack(track);
        track = drumMachine.getSequence().createTrack();
        clearDrumBeats();
        createDrumBeats();
        generateAllDrumBeatEvents();
        addMandatoryEventAtEndOfTrack();
    }

    /*
     * Generates every {@link DrumBeat}'s event.<br> This method must be called after any modification on attributes such as : <li>channel ; <li>pitch ; <li>volume ; <li>muted ;
     */
    public void generateAllDrumBeatEvents() {
        for (DrumBeat db : drumBeats) {
            db.generateEvent();
        }
    }

    private void clearDrumBeats() {
        drumBeats.clear();
    }

    private void createDrumBeats() {
        int absoluteTickPosition = 0;
        for (int beat = 1; beat <= drumMachine.getBeatsPerBar(); beat++) {
            for (int relativeTickPosition = 1; relativeTickPosition <= drumMachine.getTicksPerBeat(); relativeTickPosition++) {
                drumBeats.add(new DrumBeat(this, absoluteTickPosition++));
            }
            addEndOfBeatEvent(absoluteTickPosition);
        }
    }

    private void muteDrumBeats(boolean[] data) {
        if (data != null) {
            for (DrumBeat db : drumBeats) {
                db.setMuted(data[db.getTick()]);
            }
        }
    }

    /** Adds an event on the track that can be captured by the metaListenner. */
    private void addEndOfBeatEvent(int tick) {
        track.add(makeMetaEvent(tick));
    }

    private void addMandatoryEventAtEndOfTrack() {
        track.add(makeEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 0, drumMachine.getLengthOfBarInTicks()));

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
