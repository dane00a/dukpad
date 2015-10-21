package com.evanram.dukpad.core.midi;

public interface MidiMessage {
	public byte[] getMessage();
	public int getStatus();
	public int getLength();
}
