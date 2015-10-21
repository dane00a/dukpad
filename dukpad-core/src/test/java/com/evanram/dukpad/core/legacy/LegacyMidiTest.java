package com.evanram.dukpad.core.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.evanram.dukpad.core.legacy.LegacyColor.*;

public class LegacyMidiTest {
	@Test
	public void testGetColor() {
		final String[] colors = {RED_NAME, GREEN_NAME, YELLOW_NAME};
		final int[]    levels = {OFF, BRIGHTNESS_LOW, BRIGHTNESS_MEDIUM, BRIGHTNESS_FULL};
		final int[]    flags  = {FLAG_NORMAL, FLAG_FLASHING, FLAG_DOUBLE_BUFFERED};
		
		for(String color : colors) {
			for(int level : levels) {
				for(int flag : flags) {
					int expected;
					
					switch(color) {
					case RED_NAME:
						expected = getRed(level, flag);
						break;
						
					case GREEN_NAME:
						expected = getGreen(level, flag);
						break;
						
					case YELLOW_NAME:
						expected = getYellow(level, flag);
						break;
						
					default:
						fail("Unknown color name");
						return;
					}
					
					assertEquals(getColor(color, level, flag), expected);
				}
			}
		}
	}
}
