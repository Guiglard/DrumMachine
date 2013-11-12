package main.utils;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

public class EventMakerUtil {

    /**
     * @param command :
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

    /**
     * Returns a meta event.
     * @param tick : the position, in ticks, of the event in the sequence
     */
    public static MidiEvent makeMetaEvent(int tick) {
        MetaMessage message = new MetaMessage();
        try {
            String text = "gui";
            message.setMessage(0x01, text.getBytes(), text.getBytes().length);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return new MidiEvent(message, tick);
    }
    
}
