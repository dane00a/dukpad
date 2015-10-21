package com.evanram.dukpad.desktop.midi;

import com.evanram.dukpad.core.midi.*;

import themidibus.*;

public final class DesktopMidiHandler implements MidiHandler {
	private final MidiBus midiBus;

	public DesktopMidiHandler(MidiBus midiBus) {
		this.midiBus = midiBus;
	}

	public void sendMessage(int status, int data1, int data2) {
		midiBus.sendMessage(status, data1, data2);
	}
}
