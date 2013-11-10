package main.model.sequence;

import java.util.ArrayList;

import javax.sound.midi.Track;

import main.model.DrumMachineModel;

public class DrumTrack {

    private Track track = null;
    private int channel = 9; // aka instrument
    private int pitch = 35;
    private int volume = 100;
    private DrumMachineModel drumMachine;
    private ArrayList<DrumBeat> drumBeats = new ArrayList<>();

    public DrumTrack(DrumMachineModel drumMachine) {
        this.drumMachine = drumMachine;
        resetTrack();
    }

    public void resetTrack() {
        drumMachine.getSequence().deleteTrack(track);
        track = drumMachine.getSequence().createTrack();
        clearDrumBeats();
        createDrumBeats();
        drumBeats.get(0).setActive(true);
        drumBeats.get(2).setActive(true);
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

    public ArrayList<DrumBeat> getDrumBeats() {
        return drumBeats;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

}
