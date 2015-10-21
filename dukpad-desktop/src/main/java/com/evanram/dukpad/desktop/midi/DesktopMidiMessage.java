package com.evanram.dukpad.desktop.midi;

import com.evanram.dukpad.core.midi.MidiMessage;

public final class DesktopMidiMessage implements MidiMessage {
	private final javax.sound.midi.MidiMessage midiMessage;

	public DesktopMidiMessage(javax.sound.midi.MidiMessage midiMessage) {
		this.midiMessage = midiMessage;
	}

	public byte[] getMessage() {
		return midiMessage.getMessage();
	}

	public int getStatus() {
		return midiMessage.getStatus();
	}

	public int getLength() {
		return midiMessage.getLength();
	}
}
