package com.evanram.dukpad.core.midi;

public interface MidiHandler {
	public void sendMessage(int status, int data1, int data2);
}
