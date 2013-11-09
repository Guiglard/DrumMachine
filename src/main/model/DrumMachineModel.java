package main.model;

import java.util.Calendar;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;

import main.model.observer.BPMObserver;
import main.model.observer.BeatObserver;

public class DrumMachineModel extends BeatModel implements MetaEventListener {

    private int bpm = 60;

    @Override
    public void initialize() {
        setUpMidi();
        buildTrackAndStart();
    }

    @Override
    public void on() {
        System.out.println("start: " + sequencer.getTickPosition());
        System.out.println("bpm: " + sequencer.getTempoInBPM());
        
        sequencer.start();
    }

    @Override
    public void off() {
        sequencer.stop();
        sequencer.setTickPosition(0);
    }

    @Override
    public void setBPM(int bpm) {
        this.bpm = bpm;
        sequencer.setTempoInBPM(getBPM());
        notifyBPMObservers();
    }

    @Override
    public int getBPM() {
        return bpm;
    }

    void beatEvent() {
        notifyBeatObservers();
    }

    @Override
    public void registerObserver(BeatObserver o) {
        beatObservers.add(o);
    }

    public void notifyBeatObservers() {
        for (int i = 0; i < beatObservers.size(); i++) {
            BeatObserver observer = (BeatObserver) beatObservers.get(i);
            observer.updateBeat();
        }
    }

    @Override
    public void registerObserver(BPMObserver o) {
        bpmObservers.add(o);
    }

    public void notifyBPMObservers() {
        for (int i = 0; i < bpmObservers.size(); i++) {
            BPMObserver observer = (BPMObserver) bpmObservers.get(i);
            observer.updateBPM();
        }
    }

    @Override
    public void removeObserver(BeatObserver o) {
        int i = beatObservers.indexOf(o);
        if (i >= 0) {
            beatObservers.remove(i);
        }
    }

    @Override
    public void removeObserver(BPMObserver o) {
        int i = bpmObservers.indexOf(o);
        if (i >= 0) {
            bpmObservers.remove(i);
        }
    }

    @Override
    public void meta(MetaMessage message) {
        System.out.println("pos: " + sequencer.getTickPosition() + " length: " + sequencer.getTickLength());
        System.out.println("time: " + Calendar.getInstance().get(Calendar.SECOND));
        if (message.getType() == 47) {
            sequencer.setTickPosition(0);
            sequencer.setTempoInBPM(getBPM());
            beatEvent();
            //sequencer.start();
        }
    }

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addMetaEventListener(this);
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(getBPM());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTrackAndStart() {
        int[] trackList = { 60, 0, 46, 0 };

        sequence.deleteTrack(null);
        track = sequence.createTrack();

        makeTracks(trackList);
        track.add(makeEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 0, 4));
        try {
            sequencer.setSequence(sequence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeTracks(int[] list) {

        for (int i = 0; i < list.length; i++) {
            int key = list[i];

            if (key != 0) {
                track.add(makeEvent(ShortMessage.NOTE_ON, 4, key, 100, i));
                track.add(makeEvent(ShortMessage.NOTE_OFF, 4, key, 100, i + 1));
            }
        }
    }

    /**
     * @param command : NOTE_ON could be interpreted as the start of a note whereas NOTE_OFF would be the release.
     * @param channel : aka 'instrument'.
     * @param data1 : the pitch of the note being played.
     * @param data2 : the volume of that note.
     * @param tick : the number of the beat on which the note will be played.
     */
    public MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(command, channel, data1, data2);
            event = new MidiEvent(a, tick);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

}
