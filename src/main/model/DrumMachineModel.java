package main.model;

import java.util.ArrayList;
import java.util.Calendar;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import main.model.observer.BPMObserver;
import main.model.observer.BeatObserver;
import main.model.sequence.DrumTrack;

/**
 * Class representing the Model (MVC pattern) of the DrumMachine.<br>
 * The drum machine contains a list of drum tracks which themselves contain a list of drum beats.<br>
 * This main class contains general settings such as the number of beats per minute (bpm) and the number of ticks per beat.
 */
public class DrumMachineModel implements MetaEventListener {

    private ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
    private Sequencer sequencer;
    private Sequence sequence;
    private DrumTrack[] drumTracks = new DrumTrack[8];
    private ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
    private int bpm = 60;
    private int ticksPerBeat = 4;

    /**
     * Sets up the MIDI by creating and opening the sequencer.<br>
     * Builds all the drum tracks.
     */
    public void initialize() {
        try {
            setUpMidi();
            buildTrack();
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

    private void buildTrack() throws InvalidMidiDataException {
        sequence = new Sequence(Sequence.PPQ, getTicksPerBeat());
        sequencer.setTempoInBPM(getBpm());
        drumTracks[0] = new DrumTrack(this);
        //drumTracks[0].getTrack().add(DrumBeat.makeEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 22, getTicksPerBeat()));
        sequencer.setSequence(sequence);
    }

    public void on() {
        System.out.println("STARTING AT: " + Calendar.getInstance().get(Calendar.SECOND) + "secs");
        // sequencer.setLoopCount(-1);
        sequencer.start();
    }

    public void off() {
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
        log(1);
        if (message.getType() == 47) {
            sequencer.setTickPosition(0);
            setBpm(getBpm());
            beatEvent();
            on();
            // sequencer.start();
        }
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public int getBpm() {
        System.out.println("getbpm: " + bpm);
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
        sequencer.setTempoInBPM(bpm);
        notifyBPMObservers();
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

    void beatEvent() {
        notifyBeatObservers();
    }

    public void notifyBeatObservers() {
        for (BeatObserver observer : beatObservers) {
            observer.updateBeat();
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

    public int getTicksPerBeat() {
        return ticksPerBeat;
    }

    public void setTicksPerBeat(int ticksPerBeat) {
        this.ticksPerBeat = ticksPerBeat;
        notifyBPMObservers();
    }

    public void notifyBPMObservers() {
        for (BPMObserver observer : bpmObservers) {
            observer.updateBPM();
        }
    }

    private void log(int type) {
        if (type == 1) {
            System.out.println("tick");
        }
        System.out.println("log:");
        System.out.println("start: " + sequencer.getTickPosition());
        System.out.println("bpm: " + sequencer.getTempoInBPM());
        System.out.println("pos: " + sequencer.getTickPosition() + " length: " + sequencer.getTickLength());
        System.out.println("tempo factor: " + sequencer.getTempoFactor());
        System.out.println("endlog\n");
    }
}
