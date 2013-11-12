package main.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import main.constants.Constants;
import main.model.observer.BPMObserver;
import main.model.observer.BeatObserver;
import main.model.sequence.DrumTrack;

/**
 * Class representing the Model (MVC pattern) of the DrumMachine.<br>
 * The drum machine contains a list of drum tracks which themselves contain a list of drum beats.<br>
 * This main class contains general settings such as the number of beats per minute (bpm) and the number of ticks per beat.
 */
public class DrumMachineModel implements MetaEventListener, BPMObserver {

    private static DrumMachineModel instance = null;
    private List<BeatObserver> beatObservers = new ArrayList<>();
    private List<BPMObserver> bpmObservers = new ArrayList<>();
    private Sequencer sequencer;
    private Sequence sequence;
    private DrumTrack[] drumTracks = new DrumTrack[4];
    private int bpm = Constants.STARTING_BPM;
    private boolean running = false;
    private int beatsPerBar = Constants.DEFAULT_BEATS_PER_BAR;
    private int ticksPerBeat = Constants.DEFAULT_TICKS_PER_BEAT;

    public static DrumMachineModel getInstance() {
        if (instance == null) {
            instance = new DrumMachineModel();
        }
        return instance;
    }

    private DrumMachineModel() {
    }

    /**
     * Sets up the MIDI by creating and opening the sequencer.<br>
     * Builds all the drum tracks.
     */
    public void initialize() {
        try {
            setUpMidi();
            buildSequence();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log(0);
    }

    private void setUpMidi() throws Exception {
        sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.addMetaEventListener(this);
    }

    private void buildSequence() throws InvalidMidiDataException {
        sequence = new Sequence(Sequence.PPQ, getTicksPerBeat());
        sequencer.setTempoInBPM(getBpm());
        buildTracks();
        sequencer.setSequence(sequence);
    }

    private void buildTracks() throws InvalidMidiDataException {
        drumTracks[0] = new DrumTrack(this, Constants.VOLUME_MAX, 35, new boolean[] { true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false });
        drumTracks[1] = new DrumTrack(this, Constants.VOLUME_MAX, 42, new boolean[] { false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true });
        drumTracks[2] = new DrumTrack(this, Constants.VOLUME_MAX, 50, new boolean[] { true, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false });
        // drumTracks[3] = new DrumTrack(this, 0, 49);
    }

    public void on() {
        System.out.println(Calendar.getInstance().getTimeInMillis());
        setRunning(true);
        sequencer.start();
    }

    public void off() {
        System.out.println(Calendar.getInstance().get(Calendar.SECOND));
        setRunning(false);
        sequencer.stop();
        sequencer.setTickPosition(0);
    }

    /**
     * Event fired when the sequencer reach the end of the track.<br>
     * We need to manually start the sequence from the first tick position to loop the pattern.<br>
     * The use of {@code "LOOP_CONTINUOUSLY"} cannot be used here because the event would never be triggered.
     */
    @Override
    public void meta(MetaMessage message) {
        if (message.getType() == 47) {
            System.out.println("End of Bar");
            sequencer.setTickPosition(0);
            setBpm(getBpm());
            beatEvent();
            on();
        } else {
            System.out.println("End of beat");
        }
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        if (bpm > Constants.MIN_BPM && bpm < Constants.MAX_BPM) {
            this.bpm = bpm;
            sequencer.setTempoInBPM(getBpm());
        }
        notifyBPMObservers();
    }

    public int getBeatsPerBar() {
        return beatsPerBar;
    }

    public void setBeatsPerBar(int beatsPerBar) {
        if (beatsPerBar > Constants.MIN_BEATS_PER_BAR && beatsPerBar < Constants.MIN_TICKS_PER_BEAT) {
            this.beatsPerBar = beatsPerBar;
        }
        notifyBPMObservers();
    }

    public int getTicksPerBeat() {
        return ticksPerBeat;
    }

    public void setTicksPerBeat(int ticksPerBeat) {
        if (ticksPerBeat > Constants.MIN_TICKS_PER_BEAT && ticksPerBeat < Constants.MAX_BEATS_PER_BAR) {
            this.ticksPerBeat = ticksPerBeat;
        }
        notifyBPMObservers();
    }

    public int getLengthOfBarInTicks() {
        return getBeatsPerBar() * getTicksPerBeat();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void beatEvent() {
        notifyBeatObservers(Constants.BEAT_EVENT_CODE);
    }

    private void rebuildTrack() {
        try {
            buildSequence();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public void registerObserver(BPMObserver o) {
        bpmObservers.add(o);
    }

    public void removeObserver(BPMObserver o) {
        int i = bpmObservers.indexOf(o);
        if (i >= 0) {
            bpmObservers.remove(i);
        }
    }

    public void notifyBeatObservers(int code) {
        for (BeatObserver observer : beatObservers) {
            if (code == Constants.BEAT_EVENT_CODE) {
                observer.updateBeat();
            } else if (code == Constants.STARTED_EVENT_CODE) {
                observer.updateStarted();
            } else if (code == Constants.ENDED_EVENT_CODE) {
                observer.updateEnded();
            }
        }
    }

    public void registerObserver(BeatObserver o) {
        beatObservers.add(o);
    }

    public void removeObserver(BeatObserver o) {
        int i = beatObservers.indexOf(o);
        if (i >= 0) {
            beatObservers.remove(i);
        }
    }

    public void notifyBPMObservers() {
        for (BPMObserver observer : bpmObservers) {
            observer.updateBPM();
        }
    }

    private void log(int type) {
        // if (type == 1) {
        // System.out.println("tick");
        // }
        // System.out.println("log:");
        // System.out.println("start: " + sequencer.getTickPosition());
        // System.out.println("bpm: " + sequencer.getTempoInBPM());
        // System.out.println("pos: " + sequencer.getTickPosition() + " length: " + sequencer.getTickLength());
        // System.out.println("tempo factor: " + sequencer.getTempoFactor());
        // System.out.println("endlog\n");
    }

    @Override
    public void updateBPM() {
        rebuildTrack();
    }

}
