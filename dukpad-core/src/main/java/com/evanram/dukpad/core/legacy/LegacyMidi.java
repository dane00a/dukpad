package com.evanram.dukpad.core.legacy;

import com.evanram.dukpad.core.midi.MidiHandler;

public final class LegacyMidi {
	public static final int NOTE_OFF      = 0x80;
	public static final int SET_GRID_LEDS = 0x90;
	
	public static final int LAYOUT_XY        = 1;
	public static final int LAYOUT_DRUM_RACK = 2;
	
	private LegacyMidi() {
		throw new UnsupportedOperationException();
	}
	
	public static int getButton(int x, int y) {
		return (16 * x) + y;
	}
	
	public static int getDrumRackButton(int row, int column) {
		int tile;
		if(column < 4) {
			tile = (64 - (4 * row)) + column;
		} else if(column < 8) {
			tile = (92 - (4 * row)) + column;
		} else {
			tile = 100 + row;
		}
		
		return tile;
	}
	
	public static int getButton(int layout, int row, int column) {
		switch(layout) {
		case LAYOUT_XY: 
			return getButton(row, column);
		default: // LAYOUT_DRUM_RACK
			return getDrumRackButton(row, column);
		}
	}
	
	public static void resetLaunchpad(MidiHandler adapter) {
		adapter.sendMessage(0xB0, 0x00, 0x00);
	}
	
	public static void setGridMappingMode(MidiHandler adapter, int mode) {
		adapter.sendMessage(0xB0, 0x00, mode);
	}
}
