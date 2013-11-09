package main.model;

import java.util.ArrayList;

import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

import main.model.observer.BPMObserver;
import main.model.observer.BeatObserver;

public abstract class BeatModel {

    protected ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
    protected ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
    protected Sequencer sequencer;
    protected Sequence sequence;
    protected Track[] tracks = new Track[8];

    public abstract void initialize();

    public abstract void on();

    public abstract void off();

    public abstract void setBPM(int bpm);

    public abstract int getBPM();

    public abstract void registerObserver(BeatObserver o);

    public abstract void removeObserver(BeatObserver o);

    public abstract void registerObserver(BPMObserver o);

    public abstract void removeObserver(BPMObserver o);

}
