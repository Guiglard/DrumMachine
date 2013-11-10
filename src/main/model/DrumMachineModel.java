package main.model;

import java.util.ArrayList;
import java.util.Calendar;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

import main.model.observer.BPMObserver;
import main.model.observer.BeatObserver;
import main.model.sequence.DrumBeat;
import main.model.sequence.DrumTrack;

public class DrumMachineModel implements MetaEventListener {

    private ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
    private Sequencer sequencer;
    private Sequence sequence;
    private DrumTrack[] drumTracks = new DrumTrack[8];
    private ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
    private int bpm = 60;
    private int ticksPerBeat = 4;

    public void initialize() {
        try {
            setUpMidi();
            buildTrack();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log();
    }

    private void log() {
        System.out.println("log:");
        System.out.println("start: " + sequencer.getTickPosition());
        System.out.println("bpm: " + sequencer.getTempoInBPM());
        System.out.println("pos: " + sequencer.getTickPosition() + " length: " + sequencer.getTickLength());
        System.out.println("tempo factor: " + sequencer.getTempoFactor());
        // System.out.println("loop: " + sequencer.get();
        // System.out.println("ticksperbeat: " + getTicksPerBeat());
        System.out.println("endlog\n");
    }

    public void setUpMidi() throws Exception {
        sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.addMetaEventListener(this);
    }

    public void buildTrack() throws InvalidMidiDataException {
        sequence = new Sequence(Sequence.PPQ, getTicksPerBeat());
        sequencer.setTempoInBPM(getBpm());
        drumTracks[0] = new DrumTrack(this);
        drumTracks[0].getTrack().add(DrumBeat.makeEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 0, 4));
        sequencer.setSequence(sequence);
    }

    public void on() {
        System.out.println("on: " + sequencer.getTempoInBPM());
        System.out.println("time: " + Calendar.getInstance().get(Calendar.SECOND));
        // sequencer.setLoopCount(-1);
        sequencer.start();
    }

    public void off() {
        sequencer.stop();
        sequencer.setTickPosition(0);
    }

    @Override
    public void meta(MetaMessage message) {
        log();
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
}
